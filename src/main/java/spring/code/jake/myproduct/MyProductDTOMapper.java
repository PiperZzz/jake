package spring.code.jake.myproduct;

import java.util.ArrayList;
import java.util.function.Function;
import org.springframework.stereotype.Service;

@Service
public class MyProductDTOMapper implements Function<MyProductEntity, MyProductDTO>{
    @Override
    public MyProductDTO apply(MyProductEntity entity) {
        return new MyProductDTO(entity.getProductName(), new ArrayList<>(entity.getTags()));
    }
}
