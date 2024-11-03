package com.satish.finoutservice.model.entity.graph;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Service;

/**
 * @author : Satish Yadav
 * @purpose :
 */
@Getter
@Service
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(name = "FRIENDS")
@Entity
public class Friends {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "USER_ID" , nullable = false)
    private int userId;
    @Column(name = "FRIEND_ID" , nullable = false)
    private int friendId;
}
