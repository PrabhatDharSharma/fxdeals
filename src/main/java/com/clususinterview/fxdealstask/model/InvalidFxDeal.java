package com.clususinterview.fxdealstask.model;

import javax.persistence.*;

@Entity
@Table(name = "invalid_fx_deal")

public class InvalidFxDeal extends FxDeal{

    @Id
    @SequenceGenerator(
            name="invalid_fx_deal_sequence",
            sequenceName = "invalid_fx_deal_sequence",
            allocationSize = 50
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "invalid_fx_deal_sequence"
    )
    private Long id;
}
