package com.servlet;

/*
 * 处理师生选择环节
 * */

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;
import java.sql.*;

import java.time.LocalDateTime;
import java.time.Duration;
import java.time.format.DateTimeFormatter;

import com.entity.*;
import com.dao.*;

@WebServlet("/selection")
public class Selection extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static int Student_num;			// 剩余学生数量
	public static int current_state = 0;	// 当前轮次
	
    public List<Student> totalStuList; // 初始化学生名单
    public List<Teacher> totalTeaList; // 初始化老师名单

	
	public Queue<Integer> teacherOrder;		// 老师选取顺序
	public Queue<Integer> subjectOrder;		// 方向顺序

	// 限定选取时间
	

	/**
	 * doGet
	 *  提供学生列表，根据老师的id，返回选择该老师id的学生列表list<Student>
	 */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        System.out.println("Get in doGet from Selection");
        
     // 检查选择阶段是否已经结束
    	if (current_state != 0 && Student_num == 0) {
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write("<html><body><h1>选择阶段已经结束</h1></body></html>");
        } else {
        	
        	// get老师的方向和id
            String t_id = request.getParameter("id");	// 获取老师id
            String c_id = request.getParameter("SubId");	// 获取方向Id
            if (t_id == null || t_id.isEmpty()) {		// 检查老师id是否为空
                throw new IllegalArgumentException("Teacher ID is required");
            }
            System.out.println("Get teacher ID: " + t_id + 
            					"\tSubject ID： " + c_id);
        	Connection conn = null;

            try {
            	// 判断当前阶段
//            	setRound();
            	current_state = 1;	// 第一阶段
                // 获取数据库连接
                conn = new DAOFactory().getConn();
                // 获取老师和学生的集体信息
            	if(totalTeaList == null) {
            		getAllTheTeachers(conn);
            	} 
            	if (totalStuList == null) {
            		getAllTheStudents(conn);
            	}
            	
                // 获取教师实体信息
                Teacher teacher = getTeacher(Integer.parseInt(t_id), Integer.parseInt(c_id));
                if (teacher == null) {
                    System.out.println("Teacher not found with ID: " + t_id);
                    return;
                }
                
                List<Student> stuList = new ArrayList<>();  // 上传学生列表
                
            	if (current_state == 1 || 
        			current_state == 2 || 
					current_state == 3) {	// 志愿1， 2， 3
            		stuList = RoundFst(teacher);
                	if(stuList != null) {
                        // 将学生列表设置到教师对象中
                        teacher.setStuList(stuList);
                        for(Student stu : teacher.getStuList()) {
                        	System.out.println(stu);
                        }
                        request.setAttribute("teacher", teacher);
                        request.getRequestDispatcher("/select.jsp").forward(request, response);
                        return;
                	} else {
                        // 设置响应内容类型
                        response.setContentType("text/html;charset=UTF-8");
                        // 返回提示信息
                        response.getWriter().write("<html><body><h1>您没有学生待选择</h1></body></html>");
                	}
            	} else {	// 第二轮 && 第三轮
            		OrderSet(current_state, teacher.getFstSubject());	// 确定老师顺序和名单
            		
            		if(teacherOrder.peek() != Integer.parseInt(t_id) &&	// 未到顺序的导师
        				subjectOrder.peek() != Integer.parseInt(c_id)) {
                        // 设置响应内容类型
                        response.setContentType("text/html;charset=UTF-8");
                        // 返回提示信息
                        response.getWriter().write("<html><body><h1>还没到您选择或者选择已经结束</h1></body></html>");
                        System.out.println("Teacher: " + t_id + " isn't in the round");
                        return;
            		} else{	
            			stuList = RoundSec(teacher);
            			teacherOrder.remove();
            			subjectOrder.remove();
            			teacher.setState(current_state + 1);
                    	if(stuList != null) {
                            // 将学生列表设置到教师对象中
                            teacher.setStuList(stuList);
                            request.setAttribute("teacher", teacher);
                            request.getRequestDispatcher("/selectOnce.jsp").forward(request, response);
                    	} else {
                            // 设置响应内容类型
                            response.setContentType("text/html;charset=UTF-8");
                            // 返回提示信息
                            response.getWriter().write("<html><body><h1>您没有学生待选择</h1></body></html>");
                    	}
                    	return;
            		}	
            	} 
            
            } catch (Exception e) {
                e.printStackTrace();
                // 错误处理
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            } finally {
                // 关闭连接
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        System.out.println("Get in doPost from Selection");
        
        String teaID = request.getParameter("id");
        String SubID = request.getParameter("SubId");
        
        Date luquDate = new Date(System.currentTimeMillis());
        
        System.out.println("Tea ID: " + teaID + "\tSubID: " + SubID);
      
        String[] selectedStudentIds = request.getParameterValues("selectedStudents");
        if (selectedStudentIds != null) {
        	// 转换为整数列表或其他处理
        	System.out.println("Selected student IDs: " + Arrays.toString(selectedStudentIds));
        	for(String stuId : selectedStudentIds) {
        		// 遍历每个学生
        		// 创建对应的result实例
        		Result ret = new Result(
        				stuId,
        				teaID,
        				SubID,
        				luquDate
        				);
        		// 把该学生标记为已经被选中
        		for(Student stu : totalStuList) {
        			if(stu.getId() == Integer.parseInt(stuId)) {
        				stu.setRound(0);
        			}
        		}
        		// 调用dao写入数据库
        		ResultDAOImpl retService = new ResultDAOImpl();
        		retService.updateSelectedTable(ret);
        		// 老师数量减一
        		for(Teacher tea : totalTeaList) {
        			if(tea.getId() == Integer.parseInt(teaID) &&
    					tea.getSubjectId() == Integer.parseInt(SubID)) {
        				tea.setLeftNum(tea.getLeftNum() - 1);
        			}
        		}
        	}
        	// 后续逻辑，例如保存、更新等
        } else {
        	System.out.println("No students selected.");
        }
        // 重定向或返回响应
        response.sendRedirect("successPage.jsp");
    }
    
	// 获取学生名单 & 学生数量
	void getAllTheStudents(Connection conn) {
		System.out.println("获取学生名单");
		if(totalStuList == null) totalStuList = new ArrayList<>();
        StudentDAOImpl stuService = new StudentDAOImpl(conn);
        List<Student> students = stuService.getAllStudents();
        for(Student stu : students) {
        	String[] proName = new String[3];
        	for(Teacher tea : totalTeaList) {
        		if(tea.getId() == Integer.parseInt(stu.getP()[0])) {
        			proName[0] = tea.getName();
        		} else if(tea.getId() == Integer.parseInt(stu.getP()[1])) {
        			proName[1] = tea.getName();
        		} else if(tea.getId() == Integer.parseInt(stu.getP()[2])) {
        			proName[2] = tea.getName();
        		}
        	}
        	stu.setProName(proName);
    		System.out.println(stu);
        	totalStuList.add(stu);
        }
        if(totalStuList == null) {
        	throw new IllegalArgumentException("Student List is empty");
        }
        Student_num = totalStuList.size();
        System.out.println("getAlltheStudents stuNumber: " + Student_num);
	}
	
	// 获取老师名单
	void getAllTheTeachers(Connection conn) throws SQLException{
		TeacherDAOImpl teaService = new TeacherDAOImpl(conn);
		if(totalTeaList == null) totalTeaList = new ArrayList<>();
		System.out.println("获取老师名单");
		List<Teacher> teachers = teaService.getAllTeachers();
		if(teachers != null) {
        	for(Teacher tea : teachers) {
        		System.out.println(tea);
        		totalTeaList.add(tea);
        	}
		}
        if(totalTeaList == null) {
        	throw new IllegalArgumentException("Teacher List is empty");
        }
	}
	
	// 从totalTeaList中获取老师实体信息
	Teacher getTeacher(int t_id, int c_id) {
		if(totalTeaList == null) {
			System.out.println("total teacher List is empty");
			return null;
		}
		for(Teacher teacher : totalTeaList) {
			if(teacher.getId() == t_id && teacher.getSubjectId() == c_id) {
				return teacher;
			}
		}
		return null;
	}
    
    List<Student> RoundFst(Teacher teacher){
    	List<Student> stuList = new ArrayList<Student>();
    	String t_id = Integer.toString(teacher.getId());
    	String subjectName = teacher.getSecSubject();
        for (Student student : totalStuList) {
            String[] p = student.getP();
            if (p != null && student.getRound() != 0 &&
        		((t_id.equals(p[0]) && current_state == 1) || 
				 (t_id.equals(p[1]) && current_state == 2) || 
				 (t_id.equals(p[2]) && current_state == 3)) && 
        		subjectName.equals(student.getSecSubject())) {
            	if(student.getRound() != 0) {	// 添加未被选中的学生
                    stuList.add(student);
            	}
            }
        }
        return stuList;
    }
    
    /**
     * orderList没想好怎么分配一级学科
     * 直接按照林学院--1， 工学院 --2，信息学院--0
     */
    // 确定导师名单和导师顺序
    void OrderSet(int state, String fstSubject) {
        // 释放之前的 orderQueue
        if (teacherOrder != null) {
        	teacherOrder.clear();
        	subjectOrder.clear();
        } else {
        	teacherOrder = new LinkedList<>();
        	subjectOrder = new LinkedList<>();
        }

        // 按照剩余名额从大到小排序
        totalTeaList.sort(Comparator.comparingInt(Teacher::getLeftNum).reversed());
        
        System.out.println("----------------\tRound: " + state + "\t---------------------");

        // 筛选符合条件的导师 ID 并加入队列
        for (Teacher tea : totalTeaList) {
            if (tea.getFstSubject().equals(fstSubject)) {
                if ((state == 4 && tea.getStuList() == null) || 	// 第二轮时，只允许没有选过学生的老师选取
                    (state >= 5 && tea.getLeftNum() != 0) && tea.getState() == state) {		// 第三轮时，只允许学生名额没满的老师选取
                    
                	teacherOrder.add(tea.getId());
                    subjectOrder.add(tea.getSubjectId());
                    System.out.println("Teacher Name: " + tea.getName() + "\tleft num: " + tea.getLeftNum() + "\tstate: " + tea.getState());
                }
            }
        }
    }
    
    // roundsec 适用于第二轮和第三轮
    List<Student> RoundSec(Teacher teach){
    	List<Student> stuList = new ArrayList<Student>();
    	for(Student stu : totalStuList) {
    		if(stu.getRound() != 0 && stu.getFstSubject().equals(teach.getFstSubject())) {
    			stuList.add(stu);
    		}
    	}
    
    	return stuList;
    }    	
    
    void setRound() {
        // 定义起始时间
        String startTimeStr = "2024-12-03T00:00:00";
        LocalDateTime startTime = LocalDateTime.parse(startTimeStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        // 获取当前时间
        LocalDateTime currentTime = LocalDateTime.now();

        // 计算时间差
        Duration duration = Duration.between(startTime, currentTime);

        // 检查时间差是否超过 24 小时
        if (duration.toHours() > 24) {
            currentTime = startTime;
        }

        // 输出结果
        System.out.println("Start Time: " + startTime);
        System.out.println("Current Time: " + currentTime);
    }
}
