package quotation.model;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Currency {

    private final String SEPARATOR = ";";

    private Date date;
    private Integer id;
    private String type;
    private String name;
    private BigDecimal buyingRate;
    private BigDecimal sellingRate;
    private BigDecimal purchasingParity;
    private BigDecimal saleParity;

    public Currency(String line) throws IllegalArgumentException {
        try {
            String[] data = line.split(SEPARATOR);

            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            this.date = formatter.parse(data[0]);
            this.id = Integer.parseInt(data[1]);
            this.type = data[2];
            this.name = data[3];
            this.buyingRate = new BigDecimal(data[4].replace(",", "."));
            this.sellingRate = new BigDecimal(data[5].replace(",", "."));
            this.purchasingParity = new BigDecimal(data[6].replace(",", "."));
            this.saleParity = new BigDecimal(data[7].replace(",", "."));
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    public Currency() {
        super();
    }

    public Date getDate() {
        return date;
    }

    public Integer getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getBuyingRate() {
        return buyingRate;
    }

    public BigDecimal getSellingRate() {
        return sellingRate;
    }

    public BigDecimal getPurchasingParity() {
        return purchasingParity;
    }

    public BigDecimal getSaleParity() {
        return saleParity;
    }
}
