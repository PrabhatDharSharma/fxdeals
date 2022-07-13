package com.clususinterview.fxdealstask.model;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "valid_fx_deal")
@NoArgsConstructor
public class ValidFxDeal extends FxDeal{

    @Id
    @SequenceGenerator(
            name="valid_fx_deal_sequence",
            sequenceName = "valid_fx_deal_sequence",
            allocationSize = 50
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "valid_fx_deal_sequence"
    )
    private Long id;

}
