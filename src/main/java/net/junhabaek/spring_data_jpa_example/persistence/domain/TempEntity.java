package net.junhabaek.spring_data_jpa_example.persistence.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TempEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String message;

    public static TempEntity with(String message){
        TempEntity tempEntity = new TempEntity();
        tempEntity.message = message;
        return tempEntity;
    }

    public static TempEntity with(Long id, String message){
        TempEntity tempEntity = new TempEntity();
        tempEntity.id = id;
        tempEntity.message = message;
        return tempEntity;
    }

    public void changeMessage(String newMessage){
        this.message = newMessage;
    }

}
