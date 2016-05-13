package lt.baraksoft.summersystem.portal.view;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * Created by LaurynasC on 2016-05-12.
 */
public class ReservationPaymentView {
    private List<ReservationView> reservationsList;
    private SummerhouseView selectedSummerhouse;
    private BigDecimal summerhouseReservationPrice;
    private BigDecimal servicesReservationPrice = BigDecimal.ZERO;
    private LocalDate reservationFrom;
    private LocalDate reservationTo;
    private int pointsBefore;
    private int pointsAfter;

    public List<ReservationView> getReservationsList() {
        return reservationsList;
    }

    public void setReservationsList(List<ReservationView> reservationsList) {
        this.reservationsList = reservationsList;
    }

    public SummerhouseView getSelectedSummerhouse() {
        return selectedSummerhouse;
    }

    public void setSelectedSummerhouse(SummerhouseView selectedSummerhouse) {
        this.selectedSummerhouse = selectedSummerhouse;
    }

    public BigDecimal getSummerhouseReservationPrice() {
        return summerhouseReservationPrice;
    }

    public void setSummerhouseReservationPrice(BigDecimal summerhouseReservationPrice) {
        this.summerhouseReservationPrice = summerhouseReservationPrice;
    }

    public BigDecimal getServicesReservationPrice() {
        return servicesReservationPrice;
    }

    public void setServicesReservationPrice(BigDecimal servicesReservationPrice) {
        this.servicesReservationPrice = servicesReservationPrice;
    }

    public LocalDate getReservationFrom() {
        return reservationFrom;
    }

    public void setReservationFrom(LocalDate reservationFrom) {
        this.reservationFrom = reservationFrom;
    }

    public LocalDate getReservationTo() {
        return reservationTo;
    }

    public void setReservationTo(LocalDate reservationTo) {
        this.reservationTo = reservationTo;
    }

    public int getPointsBefore() {
        return pointsBefore;
    }

    public void setPointsBefore(int pointsBefore) {
        this.pointsBefore = pointsBefore;
    }

    public int getPointsAfter() {
        return pointsAfter;
    }

    public void setPointsAfter(int pointsAfter) {
        this.pointsAfter = pointsAfter;
    }
}
