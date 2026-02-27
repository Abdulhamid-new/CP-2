public class BoardingPass {
    private String seatNumber;
    private String gate;

    public BoardingPass(String seatNumber, String gate) {
        this.seatNumber = seatNumber;
        this.gate = gate;
    }

    @Override
    public String toString() {
        return "Boarding Pass [Seat: " + seatNumber + ", Gate: " + gate + "]";
    }
}

