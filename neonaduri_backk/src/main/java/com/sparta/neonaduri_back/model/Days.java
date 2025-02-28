package com.sparta.neonaduri_back.model;

import com.sparta.neonaduri_back.dto.post.DayRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Days {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dayId;

    @Column(nullable = false)
    private int dateNumber;

//    @ManyToOne
//    @JoinColumn(name = "POST_ID")
//    private Post post;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "places")
    private List<Places> places = new ArrayList<>();

    public Days(int dateNumber, List<Places> placesList){
        this.dateNumber=dateNumber;
        this.places=placesList;
    }
}