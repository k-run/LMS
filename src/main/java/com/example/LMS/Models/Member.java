package com.example.LMS.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "member_name", nullable = false)
    private String memberName;

    @Column(name = "member_Ship_Date_From", nullable = false)
    private Date memberShipDateFrom;

    @Column(name = "member_Ship_Date_To", nullable = false)
    private Date memberShipDateTo;

}
