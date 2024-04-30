package com.iumutdikbasan.weatherapp.general;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public abstract class BaseEntityService<E extends BaseEntity, R extends JpaRepository<E,Long>> {

    private final R repository;

    public E save(E entity){

        BaseAdditionalFields baseAdditionalFields = entity.getBaseAdditionalFields();

        if (baseAdditionalFields == null){
            baseAdditionalFields = new BaseAdditionalFields();
        }

        if (entity.getId()  == null){
            baseAdditionalFields.setCreatedDate(LocalDateTime.now());
        }


        baseAdditionalFields.setUpdatedDate(LocalDateTime.now());


        entity.setBaseAdditionalFields(baseAdditionalFields);


        return repository.save(entity);
    }



    public List<E> findAll() {
        return repository.findAll();
    }
    public Optional<E> findById(Long id) {
        return repository.findById(id);
    }
    public void delete(Long id) {
        repository.deleteById(id);
    }
    public void delete(E entity) {
        repository.delete(entity);
    }

}
