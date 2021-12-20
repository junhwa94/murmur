package org.jjh.murmur.entity;

import java.sql.Timestamp;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bno;
	
	private String writer;
	
	private String title;
	
	private String content;
	
	private int views;
	
	@CreationTimestamp
    @Column(name = "reg_date", updatable = false)
	private Timestamp regDate;
	
	@CreationTimestamp
    @Column(name = "mod_date")
	private Timestamp modDate;

}
