package responsePojo;

public class ReadResponse {
	private BookingResponse booking;
	private int bookingid;

	public void setBooking(BookingResponse booking){
		this.booking = booking;
	}

	public BookingResponse getBooking(){
		return booking;
	}

	public void setBookingid(int bookingid){
		this.bookingid = bookingid;
	}

	public int getBookingid(){
		return bookingid;
	}
}
