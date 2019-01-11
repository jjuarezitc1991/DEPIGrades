package com.depi.repository;

import org.springframework.data.repository.CrudRepository;
import com.depi.entity.Student;

public interface StudentRepository extends CrudRepository<Student, Long>{

}
