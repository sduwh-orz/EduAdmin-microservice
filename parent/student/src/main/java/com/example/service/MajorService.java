package com.example.service;

import com.example.dto.MajorDTO;
import com.example.pojo.Major;
import com.example.repository.MajorRepository;
import com.example.vo.MajorVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class MajorService {

    @Autowired
    private MajorRepository majorRepository;

    public Integer save(MajorVO vO) {
        Major bean = new Major();
        BeanUtils.copyProperties(vO, bean);
        bean = majorRepository.save(bean);
        return bean.getMajorId();
    }

    public void delete(Integer id) {
        majorRepository.deleteById(id);
    }

    public void update(Integer id, MajorVO vO) {
        Major bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        majorRepository.save(bean);
    }

    public MajorDTO getById(Integer id) {
        Major original = requireOne(id);
        return toDTO(original);
    }

    public Page<MajorDTO> query(MajorVO vO) {
        throw new UnsupportedOperationException();
    }

    public static MajorDTO toDTO(Major original) {
        MajorDTO bean = new MajorDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Major requireOne(Integer id) {
        return majorRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
