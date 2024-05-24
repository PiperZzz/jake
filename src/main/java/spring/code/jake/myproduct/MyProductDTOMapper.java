package spring.code.jake.myproduct;

import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class MyProductDTOMapper implements Function<MyProductEntity, MyProductDTO>{
    @Override
    public MyProductDTO apply(MyProductEntity entity) {
        return new MyProductDTO(entity.getProductName(), entity.getTags().stream().map(Tag::getTag).collect(Collectors.toList()));
    }
}
