package com.demo_project.Response;

import com.demo_project.entity.Teacher;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDateTime;

public class ResponseStructure<T> {
    private int id;
    private String msg;
    @CurrentTimestamp
    private LocalDateTime localDateTime;
    private Teacher teacher;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
