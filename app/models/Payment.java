package models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import play.db.ebean.Model;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import com.avaje.ebean.validation.NotNull;

@Entity
public class Payment extends Model {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	public Long id;
	
	@ManyToOne
	@NotNull
	public Invoice invoice;
	
	@NotNull
	@Temporal(value=TemporalType.DATE)
	public Date payment_date;
	
	@NotNull
	public Integer amount;
	
	public static Finder<Long, Payment> find = new Finder(Long.class, Payment.class);

	public static Payment get(Long id) {
		return find.byId(id);
	}

	public static List<Payment> all() {
		return find.all();
	}

	public static void delete(Long id) {
		find.byId(id).delete();
	}

	public static List<Payment> search(Long customerid,Integer year){
		return find.where().eq("invoice.year",year).eq("invoice.customer.id",customerid).findList();	
	}

}
