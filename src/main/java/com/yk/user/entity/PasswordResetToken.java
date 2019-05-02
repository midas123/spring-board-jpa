package com.yk.user.entity;

import java.util.Date;
import java.util.Calendar;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class PasswordResetToken {

	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;

	    @Column(nullable = false, unique = true)
	    private String token;

	    @OneToOne(targetEntity = Users.class, fetch = FetchType.EAGER)
	    @JoinColumn(nullable = false, name = "userid")
	    private Users user;

	    @Column(nullable = false)
	    private Date expiryDate;
	
	    public PasswordResetToken() {
	    	
	    }
	    
	    public Date getExpiryDate() {
	        return expiryDate;
	    }

	    public void setExpiryDate(Date expiryDate) {
	        this.expiryDate = expiryDate;
	    }

	    public void setExpiryDate(int minutes){
	        Calendar now = Calendar.getInstance();
	        now.add(Calendar.MINUTE, minutes);
	        this.expiryDate = now.getTime();
	    }

	    public boolean isExpired() {
	        return new Date().after(this.expiryDate);
	    }
}
