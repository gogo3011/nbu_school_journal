package com.example.schooljournal.services;

import com.example.schooljournal.data.entity.user.Student;
import com.example.schooljournal.data.entity.user.User;
import com.example.schooljournal.data.repository.StudentRepository;
import com.example.schooljournal.data.repository.specifications.StudentSpecifications;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class StudentService extends GenericService<Student, StudentRepository> {
    public StudentService(StudentRepository repository) {
        super(repository);
    }

    @PreAuthorize("hasAuthority(T(com.example.schooljournal.config.Permission).VIEW_STUDENTS.getAuthority())")
    public List<Student> getStudentsForParentIds(Set<Long> parentIds, Pageable pageable) {
        return repository.findAll(StudentSpecifications.haveParentsWithIds(parentIds), pageable).toList();
    }

    @PreAuthorize("hasAuthority(T(com.example.schooljournal.config.Permission).VIEW_STUDENTS.getAuthority())")
    public List<Student> getStudentsForTeacherId(Set<Long> ids, Pageable pageable) {
        return repository.findAll(StudentSpecifications.haveTeachersWithIds(ids), pageable).toList();
    }

    @PreAuthorize("hasAuthority(T(com.example.schooljournal.config.Permission).VIEW_STUDENTS.getAuthority())")
    public List<Student> getStudentsForSchoolId(Set<Long> ids, Pageable pageable) {
        return repository.findAll(StudentSpecifications.haveSchoolsWithIds(ids), pageable).toList();
    }

    @Override
    @PreAuthorize("hasAuthority(T(com.example.schooljournal.config.Permission).VIEW_ALL_STUDENTS.getAuthority())")
    public List<Student> getAll(Integer pageNo, Integer pageSize, String sortBy) {
        return super.getAll(pageNo, pageSize, sortBy);
    }

    @Override
    @PreAuthorize("hasAuthority(T(com.example.schooljournal.config.Permission).VIEW_ALL_STUDENTS.getAuthority())")
    public List<Student> getAll(Pageable paging) {
        return super.getAll(paging);
    }

    @Override
    @PreAuthorize("hasAuthority(T(com.example.schooljournal.config.Permission).CREATE_EDIT_STUDENT.getAuthority())")
    public Student save(Student toSave, User requester) {
        return super.save(toSave, requester);
    }

    @Override
    public long count() {
        return super.count();
    }

    @Override
    @PreAuthorize("hasAuthority(T(com.example.schooljournal.config.Permission).CREATE_EDIT_STUDENT.getAuthority())")
    public Student delete(Student toDelete, User requester) {
        return super.delete(toDelete, requester);
    }

    @Override
    @PreAuthorize("hasAuthority(T(com.example.schooljournal.config.Permission).CREATE_EDIT_STUDENT.getAuthority())")
    public Student delete(long toDeleteId, User requester) {
        return super.delete(toDeleteId, requester);
    }
}
