package com.example.springsocial.model.classes;

import com.example.springsocial.model.class_user.ClassUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "classes", schema = "web_room")
public class Classes {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "class_id", nullable = false)
    private Long classId;
    @Basic
    @Column(name = "owner_id")
    private Long ownerId;
    @Basic
    @Column(name = "class_name", length = 30)
    private String className;
    @Basic
    @Column(name = "class_code", length = 10)
    private String classCode;
    @Basic
    @Column(name = "created_date")
    private Date createdDate = new Date();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "class_id")
    private List<ClassUser> classUsers;
}
