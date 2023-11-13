package dto;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class ItemDTO {
    private String itemId;
    private String brand;
    private String model;
    private String type;
    private int qtyOnHand;
}
