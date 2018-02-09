package com.sistmng.admin;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class AdminService {

	private AdminDAO dao = new AdminDAO();

	
	//1 기초정보관리 메뉴
	public void basicInfoMenu(Scanner sc) {
		
		boolean run = true;
		
		while(run) {
			
		System.out.println("---------------------------------------------------------");
		System.out.println("1.과정 관리 2.과목 관리 3.강의실 관리 4. 교재 관리 0.종료");
		System.out.println("---------------------------------------------------------");
		
		int num = sc.nextInt();
		sc.nextLine();
		
		switch(num) {
		
		case 0 : run=false; break;
		case 1: courseList(sc); break;
		case 2: subjectList(sc);break;
		case 3: classList(sc);break;
		case 4: bookList(sc);break;
		
			}
		}
	}
	
	//1.1.과정관리
	public void courseList(Scanner sc){
		
		List<Admin>list = dao.courseList();
		
		System.out.println("--------------------------------------");
		System.out.println("과정코드 / 과정명");
		System.out.println("--------------------------------------");
		
		for(Admin m : list) {
			System.out.printf(String.format("%s / %s%n"),m.getCourseCode(),m.getCourseName());
		}
		boolean run = true;
		
		while(run) {
		
		System.out.println("--------------------------------");
		System.out.println("1.과정 입력 2.과정 삭제 0.나가기");
		System.out.println("--------------------------------");
		
		
		int num = sc.nextInt();
		sc.nextLine();
		
		System.out.print("선택 >");
		
		switch(num) {
		
		case 0 : run=false; break;
		case 1: this.courseAdd(sc); break;
		case 2: this.courseDelete(sc);break;
			}
		}
	}
	
	//1.1.1 과정입력
	public void courseAdd(Scanner sc) {
		
		System.out.print("과정명 >");
		String courseName = sc.next();
		
		
		int result = dao.courseAdd(courseName);
		
		if(result > 0) {
			System.out.printf(String.format("[%s]과정이 성공적으로 추가되었습니다.%n",courseName));
		} else {
			System.out.println("이미 존재하는 과정명입니다.");
		}
		
	}
	
	//1.1.2 과정삭제
	public void courseDelete(Scanner sc) {
		
		List<Admin> list = dao.courseDeleteList();
		
		System.out.println("삭제 가능 과정 목록입니다.");
		System.out.println("------------------------------------------");
		for(Admin m : list) {
			System.out.printf(String.format("%s / %s %n",m.getCourseCode(),m.getCourseName() ));
			
			
		}
		
		System.out.print("과정코드 >");
		String courseCode = sc.next();
		
		List<Admin> list1 = dao.courseNameList(courseCode);

		for(Admin m : list1) {
		System.out.printf(String.format("[%s] 과정을 삭제하시겠습니까 (y/n)?",m.getCourseName() ));
		}
		
		String yn = sc.next();
		
		if (yn.equalsIgnoreCase("y")) {
			
			int result = dao.courseDelete(courseCode);
			
			if (result > 0) {
			System.out.println("삭제가 완료되었습니다.");
			} else {
				System.out.println("삭제가 정상적으로 이루어지지 않았습니다.");
			}
			
		} else if (yn.equalsIgnoreCase("n")) {
			return;
		} else {
			System.out.println("올바른 입력이 아닙니다.");
			return;
		}

		
	}
	
	//1.2 과목관리
	public void subjectList(Scanner sc) {
		
		List<Admin>list = dao.subjectList();
		
		System.out.println("--------------------------");
		System.out.println("과목코드 / 과정명 / 교재명");
		System.out.println("--------------------------");
		
		for(Admin m : list) {
			System.out.printf(String.format("%s / %s / %s %n"),m.getSubjectCode(),m.getSubjectName(),m.getBookName());
		}
		boolean run = true;
		
		while(run) {
		
		System.out.println("--------------------------------");
		System.out.println("1.과목 입력 2.과목 삭제 0.나가기");
		System.out.println("--------------------------------");
		
		
		int num = sc.nextInt();
		sc.nextLine();
		
		System.out.print("선택 >");
		
		switch(num) {
		
		case 0 : run=false; break;
		case 1: this.courseAdd(sc); break;
		case 2: this.courseDelete(sc);break;
		
		
		}
			
		}
		
	}
	
	//1.2.1 과목입력
	public void subjectAdd(Scanner sc) {
		
		System.out.print("과목명 >");
		String subjectName = sc.next();
		
		
		
		int result = dao.subjectAdd(subjectName);
		
		if(result > 0) {
			System.out.printf(String.format("[%s]과목이 성공적으로 추가되었습니다.%n", subjectName));
		} else {
			System.out.println("이미 존재하는 과목명입니다.");
		}
	}
	
	//1.2.2 과목삭제
	public void subjectDelete(Scanner sc) {
		
		List<Admin>list = dao.subjectDeleteList();
		
		System.out.println("삭제 가능 과목 목록입니다.");
		System.out.println("------------------------------------------");
		for(Admin m : list) {
			System.out.printf(String.format("%s / %s %n",m.getSubjectCode(),m.getSubjectName() ));
		}
		
		System.out.print("과목코드 >");
		String subjectCode = sc.next();
		
		List<Admin>list1 = dao.subjectList(subjectCode);
		
		for(Admin m : list1) {
		System.out.printf(String.format("[%s] 과목을 삭제하시겠습니까 (y/n)?",m.getSubjectName()));
		}
		
		String yn = sc.next();
		
		if (yn == "y" && yn == "Y") {
			
			int result = dao.subjectDelete(subjectCode);
			
			if (result > 0) {
			System.out.println("삭제가 완료되었습니다.");
			} else {
				System.out.println("삭제가 정상적으로 이루어지지 않았습니다.");
			}
			
		} else if (yn == "n" && yn == "N") {
			return;
		} else {
			System.out.println("올바른 입력이 아닙니다.");
			return;
		}
		
	}
	
	//1.3 강의실 관리
	public void classList(Scanner sc) {
		
		List<Admin>list = dao.classList();
		
		System.out.println("--------------------------");
		System.out.println("강의실 고유번호 / 강의실명 / 정원");
		System.out.println("--------------------------");
		
		for(Admin m : list) {
			System.out.printf(String.format("%s / %s / %s %n"),m.getClassCode(),m.getClassName(),m.getClassQuota());
		}
		boolean run = true;
		
		while(run) {
		
		System.out.println("------------------------------------");
		System.out.println("1.강의실 입력 2.강의실 삭제 0.나가기");
		System.out.println("------------------------------------");
		
		
		int num = sc.nextInt();
		sc.nextLine();
		
		System.out.print("선택 >");
		
		switch(num) {
		
		case 0 : run=false; break;
		case 1: this.classAdd(sc); break;
		case 2: this.classDelete(sc);break;
		
		
		}
			
		}
	}
	
	//1.3.1 강의실 입력
	public void classAdd(Scanner sc) {

		System.out.print("관리자 아이디>");
		String mid = sc.next();
		System.out.print("강의실명 >");
		String className = sc.next();
		System.out.print("강의실 정원 >");
		int classQuota = sc.nextInt();
		sc.nextLine();
		
		Admin a = new Admin();
		a.setMid(mid);
		a.setClassName(className);
		a.setClassQuota(classQuota);
		
		int result = dao.classAdd(a);
		
		if(result > 0) {
			System.out.printf(String.format("[%s / 정원: %d명 ]이 성공적으로 추가되었습니다.%n",className,classQuota));
		} else {
			System.out.println("이미 존재하는 강의실입니다.");
		}
	}
	
	//1.3.2 강의실 삭제
	public void classDelete(Scanner sc) {
		
		List<Admin>list = dao.classDeleteList();
		
		System.out.println("삭제 가능 강의실 목록입니다.");
		System.out.println("------------------------------------------");
		for(Admin m : list) {
			System.out.printf(String.format("%s / %s %n",m.getClassCode(),m.getClassName() ));
		}
		
		System.out.print("강의실코드 >");
		String classCode = sc.next();
		
		List<Admin>list1 = dao.classList(classCode);
		
		for(Admin m : list1) {
			
			System.out.printf(String.format("[%s] 강의실을 삭제하시겠습니까 (y/n)?",m.getClassName() ));
		}
		
		String yn = sc.next();
		
		if (yn == "y" && yn == "Y") {
			
			int result = dao.classDelete(classCode);
			
			if (result > 0) {
			System.out.println("삭제가 완료되었습니다.");
			} else {
				System.out.println("삭제가 정상적으로 이루어지지 않았습니다.");
			}
			
		} else if (yn == "n" && yn == "N") {
			return;
		} else {
			System.out.println("올바른 입력이 아닙니다.");
			return;
		}
	}
	
	//1.4 교재관리
	public void bookList(Scanner sc) {
		
		List<Admin>list = dao.bookList();
		
		System.out.println("----------------------------");
		System.out.println("교재번호 / 교재이름 / 출판사");
		System.out.println("----------------------------");
		
		for(Admin m : list) {
			System.out.printf(String.format("%s / %s / %s %n"),m.getBookCode(),m.getBookName(),m.getBookPublisher());
		}
		boolean run = true;
		
		while(run) {
		
		System.out.println("--------------------------------");
		System.out.println("1.교재 입력 2.교재 삭제 0.나가기");
		System.out.println("--------------------------------");
		
		
		int num = sc.nextInt();
		sc.nextLine();
		
		System.out.print("선택 >");
		
		switch(num) {
		
		case 0 : run=false; break;
		case 1: this.bookAdd(sc); break;
		case 2: this.bookDelete(sc);break;
		
		
		}
			
		}
		
	}
	
	//1.4.1 교재입력
	public void bookAdd(Scanner sc) {
		
		System.out.print("교재명 >");
		String bookName = sc.next();
		
		System.out.print("출판사 >");
		String bookPublisher = sc.next();
		
		int result = dao.bookAdd(bookName,bookPublisher);
		
		if(result > 0) {
			System.out.printf(String.format("[%s / %s ]이(가) 성공적으로 추가되었습니다.%n",bookName,bookPublisher));
		} else {
			System.out.println("이미 존재하는 교재입니다.");
		}
	}
	
	//1.4.2 교재삭제
	public void bookDelete(Scanner sc) {
		

		List<Admin>list = dao.bookDeleteList();
		
		System.out.println("삭제 가능 교재 목록입니다.");
		System.out.println("------------------------------------------");
		for(Admin m : list) {
			System.out.printf(String.format("%s / %s / %s %n",m.getBookCode(),m.getBookName(),m.getBookPublisher() ));
		}
		
		System.out.print("교재번호 >");
		String bookCode = sc.next();
		
		List<Admin>list1 = dao.bookList(bookCode);
		
		for(Admin m : list1) {
		System.out.printf(String.format("[%s / %s / %s] 교재를 삭제하시겠습니까 (y/n)?", m.getBookCode(),m.getBookName(),m.getBookPublisher()));
		}
		
		String yn = sc.next();
		
		if (yn == "y" && yn == "Y") {
			
			int result = dao.bookDelete(bookCode);
			
			if (result > 0) {
			System.out.println("삭제가 완료되었습니다.");
			} else {
				System.out.println("삭제가 정상적으로 이루어지지 않았습니다.");
			}
			
		} else if (yn == "n" && yn == "N") {
			return;
		} else {
			System.out.println("올바른 입력이 아닙니다.");
			return;
		}
		
	}
	
	//-----------------------------------------------
	
	//2. 강사 계정 관리
	
	public void InstructorMenu(Scanner sc) {
	
		boolean run = true;
		
		while(run) {
			
		System.out.println("------------------------------------------");
		System.out.println("1.강사 목록 2.강사 등록 3.강사 삭제 0.종료");
		System.out.println("------------------------------------------");
		
		int num = sc.nextInt();
		sc.nextLine();
		
		switch(num) {
		
		case 0 : run=false; break;
		case 1: this.InstructorList(sc); break;
		case 2: this.InstructorAdd(sc);break;
		case 3: this.InstructorDelete(sc);break;
		
		}
			
		}
		
	}
	
	
	//2.1 강사 목록
	
	public void InstructorList(Scanner sc) {
	
		List<Admin>list = this.dao.InstructorList();
		if(list.size() == 0) {
			System.out.println("조회된 정보가 없습니다.");
		}else {
		
		System.out.println("------------------------------------------------------------------");
		System.out.println("강사번호 / 이름 / 주민번호 / 전화번호 / 강의가능과목 / 강사등록일 ");
		System.out.println("------------------------------------------------------------------");
		
		for(Admin m : list) {
			System.out.printf(String.format("%s / %s / %s / %s / %s / %s%n", m.getMid(),m.getName_(),m.getSsn(),m.getPhone(),m.getSubjectName(),m.getInstructorRegDate()));
		}
		boolean run = true;
		
		while(run) {
		
		System.out.println("------------------------------------------------------------");
		System.out.println("1.상세보기 2.강의가능과목 추가 3.강의가능과목 삭제 0.나가기");
		System.out.println("------------------------------------------------------------");
		
		
		int num = sc.nextInt();
		sc.nextLine();
		
		System.out.print("선택 >");
		
		switch(num) {
		
		case 0 : run=false; break;
		case 1: this.InstructorSubjectDetailList(sc); break;
		case 2: this.InstructorSubjectAdd(sc);break;
		case 3: this.InstructorSubjectDelete(sc);break;
				}

			}
		}		
		
	}
	
	//2.1.1 강사상세보기
	
	public void InstructorSubjectDetailList(Scanner sc) {
		
		
		System.out.print("강사번호 >");
		String value = sc.next();
		
		
		
		List<Admin>list1 = this.dao.midNameList(value);
		
		List<Admin>list2 = this.dao.InstructorSubjectDetailList(value);
		
		if(list2.size()>0) {
			
			for (Admin m1 : list1) {
				System.out.printf(String.format("[ %s / %s ] 강사님 %n", m1.getMid(),m1.getName_()));
			} 
			
			System.out.println("--------------------------------------------------------------------------------------------------------------------------");
			
			for(Admin m2 : list2) {
				System.out.printf(String.format(" %s / %s / %s / %s / %s / %s / %s / %s / %s %n", m2.getSubjectName(),m2.getOpenSubCode(),m2.getOpenSubStartDate(),m2.getOpenSubCloseDate(),m2.getCourseName(),m2.getOpenCoStartDate(),m2.getOpenCoCloseDate(),m2.getClassName(),m2.getInstStatus() ));
			}
			System.out.println("--------------------------------------------------------------------------------------------------------------------------");
			
			
		} else {
			System.out.println("검색 결과가 없습니다.");
		}
		
		
	}
	
	
	//2.1.2 강의가능과목 추가
	
	public void InstructorSubjectAdd(Scanner sc) {
		
	
	System.out.print("강사번호 > ");
	String mid = sc.next();
	System.out.print("추가과목번호 > ");
	String subjectCode = sc.next();
	
	List<Admin>list = dao.subjectList(subjectCode);
	
	int result = dao.InstructorSubjectAdd(mid,subjectCode);
	
	if(result > 0) {
		for(Admin m : list) {
		System.out.printf(String.format("[%s / %s ]과목이 성공적으로 추가되었습니다.%n",m.getSubjectCode(),m.getSubjectName()));
		}
	} else {
		System.out.println("이미 존재하는 과목입니다.");
	}
	
	}
	
	
	//2.1.3 강의가능과목 삭제
	
	public void InstructorSubjectDelete(Scanner sc) {
		

		System.out.print("강사번호 >");
		String mid = sc.next();
		
		System.out.print("삭제과목번호 >");
		String subjectCode = sc.next();
		
		List<Admin>list = dao.subjectList(subjectCode);
		
		String yn = sc.next();
		
		for (Admin m : list) {
		System.out.printf(String.format("[ %s / %s ] 과목을 삭제하시겠습니까 (y/n)?",m.getSubjectCode(),m.getSubjectName()));
		}
		
		if (yn.equalsIgnoreCase("y")) {
			
			int result = dao.InstructorSubjectDelete(mid, subjectCode);
			
			if (result > 0) {
			for (Admin m : list) {
			System.out.printf(String.format("[ %s / %s ] 과목이 성공적으로 삭제되었습니다.",m.getSubjectCode(),m.getSubjectName()));
			}
			} else {
				System.out.println("삭제가 정상적으로 이루어지지 않았습니다.");
			}
			
		} else if (yn.equalsIgnoreCase("n")) {
			return;
		} else {
			System.out.println("올바른 입력이 아닙니다.");
			return;
		}
		
	}
	//2.2 강사 등록
	
	public void InstructorAdd(Scanner sc) {
		
		
		System.out.print("강사명 >");
		String name_ = sc.next();
		
		List<Admin>list = this.dao.InstructorAddList(name_);
		
		if(list.size() > 0 ) {
			
			System.out.println("--------------------------------------------------------------");
			System.out.println("회원번호 / 이름 / 주민번호 / 전화번호 / 회원 등록일 / 회원구분");
			System.out.println("--------------------------------------------------------------");
			
			for(Admin m : list) {
				System.out.printf(String.format("%s / %s / %s / %s / %s / %s %n",m.getMid(),m.getName_(),m.getSsn(),m.getPhone(),m.getMemberRegDate(),m.getMemberStatus()));
			}
			
			System.out.println("--------------------------------------------------------------");
			
			System.out.print("회원 조회 결과가 존재합니다. 기존 회원을 강사로 등록 하시겠습니까? (Y/N ");
			String yn = sc.next();
			if (yn.equalsIgnoreCase("y")) {
				System.out.print("회원번호 >");
				String mid = sc.next();
				
				this.dao.InstructorAdd(mid);
				
				System.out.println("강사등록이 완료되었습니다.");
				
			}  else if (yn.equalsIgnoreCase("n")) {
				return;
			} else if (list.size() <= 0) {
				//2.2 강사등록 신규회원
				System.out.print("회원 조회 결과가 없습니다. 신규 등록 하시겠습니까? (Y/N) ");
				if (yn.equalsIgnoreCase("y")) {
					System.out.print("주민번호 뒷자리 >");
					String ssn = sc.next();
					System.out.print("전화번호 >");
					String phone = sc.next();
					System.out.print("등록일 >");
					String memberRegDate = sc.next();
					
					this.dao.InstructorAddNew(name_,ssn,phone,memberRegDate);
					
					System.out.println("강사등록이 완료되었습니다.");
				} else if (yn.equalsIgnoreCase("n")) {
					return;
				}
			} 
			
		}
		
		
		
	}
	
	//2.3 강사 삭제
	
	public void InstructorDelete(Scanner sc) {
		
		List<Admin>list = this.dao.InstructorDeleteList();
		
		System.out.println("-------------------------------------------------------------------");
		System.out.println(" 강사번호 / 이름 / 주민번호 / 전화번호 / 강의가능과목 / 강사등록일 ");
		System.out.println("-------------------------------------------------------------------");
		
		for(Admin m : list) {
			System.out.printf(String.format("%s / %s / %s / %s / %s / %s %n", m.getMid(),m.getName_(),m.getSsn(),m.getPhone(),m.getSubjectName(),m.getInstructorRegDate() ));
		}
		
		System.out.print("번호선택 >");
		String mid = sc.next();
		
		List<Admin>list1 = this.dao.midNameList(mid);
		
		for (Admin m : list1) {
			System.out.printf(String.format("[ %s / %s ] 강사를 삭제 하시겠습니까?", m.getMid(),m.getName_() ));
			
			String yn = sc.next();
			if(yn.equalsIgnoreCase("y")) {
				this.dao.InstructorDelete(mid);
				
				System.out.printf(String.format("[ %s / %s ] 강사가 삭제되었습니다.", m.getMid(),m.getName_()));
				
			} else if (yn.equalsIgnoreCase("n")) {
				return;
			} else {
				return;
			}
			
		}
		
	}
	
	
	
	//-----------------------------------------------
	
	//3. 개설 과정 관리
	
	public void openCourseMenu(Scanner sc) {
	
		boolean run = true;
		
		while(run) {
			
		System.out.println("------------------------------------------");
		System.out.println("1.개설 과정 출력 2.개설 과정 등록 3.개설 과정 삭제 0.종료");
		System.out.println("------------------------------------------");
		
		int num = sc.nextInt();
		sc.nextLine();
		
		switch(num) {
		
		case 0 : run=false; break;
		case 1: this.openCourseList(sc); break;
		case 2: this.openCourseAdd(sc);break;
		case 3: break;
		
		}
			
		}
		
	}
	
	//3.1 개설 과정 출력
	private void openCourseList(Scanner sc) {
		System.out.println("------------------------------------------");
		System.out.println("개설 과정 코드 / 개설 과정 명 / 개설 과정 기간 / 강의실명 / 개설 과목 등록 갯 수 / 수강생 인원");
		System.out.println("------------------------------------------");
		List<Admin> list = dao.openCourseList();
		for(Admin a : list) {
			System.out.printf("%s %s %s~%s %s %s %s %n",a.getOpenCoCode(),a.getCourseName(),a.getOpenCoStartDate(),a.getOpenCoCloseDate(),a.getClassName(),a.getCount_(),a.getCount_studentHistory());
		}
		System.out.println("------------------------------------------");
		System.out.println("1.상세보기 0.나가기");
		int selectNo = sc.nextInt();
		sc.nextLine();
		System.out.println("개설과정코드 >");
		String value = sc.next();
		switch(selectNo) {
		  case 1 : this.openCourseDetailList(sc, value);break;
		  case 0 : break;
		}
	}
	
	
	//3.1.1 개설 과정 상세보기
	private void openCourseDetailList(Scanner sc, String value) {
		List<Admin> list = dao.openCourseDetailList(value);
		
		if(list.size()<=0) {
			System.out.println("존재하지 않는 코드입니다.");
		}else {
			System.out.println("------------------------------------------");
			System.out.println("개설 과정 코드 / 개설 과정 명 / 개설 과정 기간 / 강의실명");
			System.out.println("------------------------------------------");
			for(Admin a : list) {
			System.out.printf("%s %s %s ~ %s %s %n",a.getOpenCoCode(),a.getCourseName(),a.getOpenCoStartDate(),a.getOpenCoCloseDate(),a.getClassName());
			}
			System.out.println("------------------------------------------");
			System.out.println("1.과목 상세보기 2.수강생 상세보기 0.나가기");
			int selectNo = sc.nextInt();
			sc.nextLine();
			switch(selectNo) {
			  case 1 : this.subjectDetailList(value);break;
			  case 2 : this.studentDetailList(value);break;
			  case 0 : break;
			}
		}
	}
	
	//3.1.1.1 과목 상세보기
	private void subjectDetailList(String value) {
		List<Admin> list = dao.subjectDetailList(value);
		System.out.println("------------------------------------------");
		System.out.println("개설 과목 명 / 개설 과목 기간 / 교재명 / 강사명");
		System.out.println("------------------------------------------");
		for(Admin a : list) {
			System.out.printf("%s %s %s ~ %s %s %n",a.getSubjectName(),a.getOpenSubStartDate(),a.getOpenSubCloseDate(),a.getBookName(),a.getName_());
			}
		System.out.println("------------------------------------------");
	}
	
	
	//3.1.1.2 수강생 보기
	private void studentDetailList(String value) {
		List<Admin> list = dao.studentDetailList(value);
		System.out.println("------------------------------------------");
		System.out.println("수강생 / 주민번호 뒷자리 / 전화번호 / 수강생 등록일 / 수료일");
		System.out.println("------------------------------------------");
		for(Admin a : list) {
			System.out.printf("%s %s %s %s %s %n",a.getName_(),a.getSsn(),a.getPhone(),a.getSstudentRegDate(),a.getCompletionCheck());
			}
		System.out.println("------------------------------------------");
	}		
	
	//3.2 개설 과정 등록
	private void openCourseAdd(Scanner sc) {
		
		List<Admin> list = dao.courseList();
		
		int ab = 0;
		
		String classCodeSc = sc.next();
		
		System.out.println("------------------------------------------");
		System.out.println("과정코드 / 과정명");
		System.out.println("------------------------------------------");
		
		for(Admin a : list) {
			System.out.printf("%s %s %n",a.getCourseCode(), a.getCourseName());
			}
		
		System.out.println("------------------------------------------");
		System.out.println("개설 과정 코드 >");
		String value = sc.next();
		
		for(Admin a : list) {
			if(a.getCourseCode().equals(value)) {
				
				Admin m = new Admin();
				System.out.println("개설 과정 시작일>");
				String openCoStartDate = sc.next();
				System.out.println("개설 과정 종료일>");
			    String oepnCoCloseDate = sc.next();
			    m.setOpenCoCode(value);
			    m.setOpenCoStartDate(LocalDate.parse(openCoStartDate));
			    m.setOpenCoCloseDate(LocalDate.parse(oepnCoCloseDate));
			    
			    ab = dao.openCourseAdd(m);
			    return;
			}
		}
		System.out.println("존재 하지 않는 과정입니다.");
	}
	
	
	
	
	
	//3.3 개설 과정 삭제
	
	//-----------------------------------------------
	
	
	//4. 개설 과목 관리
	//4.1 과정 상세보기
	//4.2 과목 등록
	//4.3 과목 삭제
	
	//-----------------------------------------------
	
	//5. 수강생 관리
	//5.1 수강생 등록
	//5.2 수강생 출력(중도탈락 관리)
	//5.3 수강생 과정 등록
	//5.4 수강생 삭제
	//5.4.1 수강생 과정 삭제
	//5.4.2 수강생 삭제
	
	//-----------------------------------------------
	
	//6. 성적 조회
	//6.1 개설 과정 보기
	//6.1.1 상세보기
	//6.1.1.1 과목별 성적 보기
	//6.2 수강생 검색
	//6.2.1 상세보기 (과정)
	//6.2.1.1 상세보기(과목)
	//6.3 성적 정보
	//6.3.2 이름검색
	//6.3.3 시험코드 검색
	
}
