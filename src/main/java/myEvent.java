import java.util.Date;

public class myEvent extends Task {
    DateTime startDatetime, endDatetime;

    myEvent(String description, String startDatetimeInput, String endDatetimeInput) {
        super(description);

        String[] startDatetimeArr = startDatetimeInput.split("\\s+");
        String[] endDatetimeArr = endDatetimeInput.split("\\s+");

        if (startDatetimeArr.length == 2 && endDatetimeArr.length == 2) {
            this.startDatetime = new DateTime(startDatetimeArr[0], startDatetimeArr[1]);
            this.endDatetime = new DateTime(endDatetimeArr[0], endDatetimeArr[1]);
        } else {
            this.startDatetime = new DateTime(startDatetimeArr[0]);
            this.endDatetime = new DateTime(endDatetimeArr[0]);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (this.completed)
            sb.append("[E] [x] ");
        else
            sb.append("[E] [ ] ");

        sb.append(this.description
                + " at: "
                + this.startDatetime
                + " to "
                + this.endDatetime);
        return sb.toString();
    }
}
