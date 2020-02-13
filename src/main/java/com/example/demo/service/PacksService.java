package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.PacksDao;

@Service
public class PacksService {
	@Autowired
	PacksDao packsdao;
}
