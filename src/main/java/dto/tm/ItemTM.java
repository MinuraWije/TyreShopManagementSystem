package dto.tm;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class ItemTM {
    private String itemId;
    private String brand;
    private String model;
    private String type;
    private int qtyOnHand;
}
