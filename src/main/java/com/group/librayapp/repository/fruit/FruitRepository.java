package com.group.librayapp.repository.fruit;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.group.librayapp.dto.fruit.request.CereateFruitReqeust;
import com.group.librayapp.dto.fruit.request.UpdateFruitRequest;
import com.group.librayapp.dto.fruit.response.FruitResponse;

@Repository
public interface FruitRepository {
    void postFruitSet(CereateFruitReqeust request);
    Boolean isFruitExist(long id);
    void putFruitSet(UpdateFruitRequest request);
    List<FruitResponse> getFruitStatus();
    
}
