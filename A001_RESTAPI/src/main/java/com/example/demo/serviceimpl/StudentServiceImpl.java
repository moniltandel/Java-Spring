package com.example.demo.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.model.Student;
import com.example.demo.repo.StudentRepo;
import com.example.demo.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	StudentRepo repo;
	
	@Override
	public Student addStudent(Student st) {
		// TODO Auto-generated method stub
		return repo.save(st);
	}

	@Override
	public List<Student> allStudents(int pagenumber, int pagesize, String sortby, String sorttype) {
		// TODO Auto-generated method stub
		
		Sort sort = null;
		
		if(sorttype.equalsIgnoreCase("asc")) {
			sort = Sort.by(sortby).ascending();
		}else if(sorttype.equalsIgnoreCase("desc")) {
			sort = Sort.by(sortby).descending();
		}
		
		Pageable pageable = PageRequest.of(pagenumber, pagesize,sort);
		Page<Student> pages = repo.findAll(pageable);
		
		List<Student> allStudents = pages.getContent();
		return allStudents;
	}

	@Override
	public Student studentById(int id) {
		// TODO Auto-generated method stub
		return repo.findById(id).orElseThrow();
	}

	@Override
	public void deleteStudent(int id) {
		// TODO Auto-generated method stub
		
		repo.deleteById(id);
		
	}

	@Override
	public Student updateStudent(Student st, int id) {
		// TODO Auto-generated method stub
		st.setId(id);
		return repo.save(st);
	}
	
}
