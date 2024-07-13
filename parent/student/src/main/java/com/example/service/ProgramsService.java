package com.example.service;

import com.example.dto.ProgramsDTO;
import com.example.pojo.Programs;
import com.example.repository.ProgramsRepository;
import com.example.vo.ProgramsVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ProgramsService {

    @Autowired
    private ProgramsRepository programsRepository;

    public Integer save(ProgramsVO vO) {
        Programs bean = new Programs();
        BeanUtils.copyProperties(vO, bean);
        bean = programsRepository.save(bean);
        return bean.getProgramId();
    }

    public void delete(Integer id) {
        programsRepository.deleteById(id);
    }

    public void update(Integer id, ProgramsVO vO) {
        Programs bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        programsRepository.save(bean);
    }

    public ProgramsDTO getById(Integer id) {
        Programs original = requireOne(id);
        return toDTO(original);
    }

    public Page<ProgramsDTO> query(ProgramsVO vO) {
        throw new UnsupportedOperationException();
    }

    public static ProgramsDTO toDTO(Programs original) {
        ProgramsDTO bean = new ProgramsDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Programs requireOne(Integer id) {
        return programsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
