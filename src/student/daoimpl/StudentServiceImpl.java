package student.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import student.dao.StudentService;
import student.db.database;
import student.model.Student;

public class StudentServiceImpl implements StudentService {
	private Connection con;
	

	

	public StudentServiceImpl(Connection con) {
		super();
		this.con = con;
	}

	@Override
	public String createStudent(List<Student> lst) {
		// TODO Auto-generated method stub
		String str=null;
		database dobj=new database();
		Connection con= dobj.getConnection();
		Student stud=lst.get(0);
		int i=0;
		
		try {
			PreparedStatement pstate=con.prepareStatement("insert into Student values(?,?,?,?,?,?)");
			pstate.setInt(1, stud.getStud_id());
			pstate.setString(2,stud.getStud_name());
			pstate.setString(3,stud.getStud_DOB());
			pstate.setString(4,stud.getStud_add());
			pstate.setDouble(5,stud.getStud_per());
			pstate.setInt(6,stud.getCourse_id());
			i=pstate.executeUpdate();
			
			if(i>0)
			{
				str="valid";
				System.out.println("You have successfully save the information !");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("text1..");
			System.out.println(e.getMessage());
			str=e.getMessage();
			e.printStackTrace();
		}
		
		return str;
	}

	@Override
	public List<Student> retriveStudent(int stud_id) {
		// TODO Auto-generated method stub
	//	database dobj=new database();
	//	Connection con = (Connection) dobj.getConnection();
		//int i=0;
		
		return null;
	}

	@Override
	public int deleteStudent(int stud_id) {
		// TODO Auto-generated method stub
		
		int i=0;
		Connection con=database.getConnection();
		List<Student>lst=new ArrayList();
		
		try {
			PreparedStatement pstate=con.prepareStatement("delete from Student where stud_id =?");
			
			Student stud = lst.get(0);
			pstate.setInt(1, stud.getStud_id());
			i=pstate.executeUpdate();
			
			if(i>0)
			{
				System.out.println("You have successfully delete the information !");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return i;
	}

	@Override
	public List<Student> getAllStudent() {
		// TODO Auto-generated method stub
		
		Connection con=database.getConnection();
		
		List<Student> lst=new ArrayList();
		
		try {
			String str="select * from Student";
			PreparedStatement state=con.prepareStatement(str);
			System.out.println(state);
			ResultSet rs= state.executeQuery(str);
			
			while(rs.next()) {
				Student slst=new Student(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDouble(5),rs.getInt(6),rs.getString(7));
				lst.add(slst);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(lst);
		return lst;
	}
	

}

