package com.dawes.serviciosImpl;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import com.dawes.modelo.Menu;
import com.dawes.repositorio.MenuRepository;
import com.dawes.servicios.ServicioMenu;

@Service
public class ServicioMenuImpl implements ServicioMenu {

	@Autowired
	private MenuRepository mr;

	@Override
	public <S extends Menu> S save(S entity) {
		return mr.save(entity);
	}

	@Override
	public <S extends Menu> List<S> saveAll(Iterable<S> entities) {
		return mr.saveAll(entities);
	}

	@Override
	public <S extends Menu> Optional<S> findOne(Example<S> example) {
		return mr.findOne(example);
	}

	@Override
	public List<Menu> findAll(Sort sort) {
		return mr.findAll(sort);
	}

	@Override
	public void flush() {
		mr.flush();
	}

	@Override
	public Page<Menu> findAll(Pageable pageable) {
		return mr.findAll(pageable);
	}

	@Override
	public <S extends Menu> S saveAndFlush(S entity) {
		return mr.saveAndFlush(entity);
	}

	@Override
	public <S extends Menu> List<S> saveAllAndFlush(Iterable<S> entities) {
		return mr.saveAllAndFlush(entities);
	}

	@Override
	public List<Menu> findAll() {
		return mr.findAll();
	}

	@Override
	public List<Menu> findAllById(Iterable<Integer> ids) {
		return mr.findAllById(ids);
	}

	@Override
	public void deleteInBatch(Iterable<Menu> entities) {
		mr.deleteInBatch(entities);
	}

	@Override
	public <S extends Menu> Page<S> findAll(Example<S> example, Pageable pageable) {
		return mr.findAll(example, pageable);
	}

	@Override
	public Optional<Menu> findById(Integer id) {
		return mr.findById(id);
	}

	@Override
	public void deleteAllInBatch(Iterable<Menu> entities) {
		mr.deleteAllInBatch(entities);
	}

	@Override
	public boolean existsById(Integer id) {
		return mr.existsById(id);
	}

	@Override
	public <S extends Menu> long count(Example<S> example) {
		return mr.count(example);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		mr.deleteAllByIdInBatch(ids);
	}

	@Override
	public <S extends Menu> boolean exists(Example<S> example) {
		return mr.exists(example);
	}

	@Override
	public void deleteAllInBatch() {
		mr.deleteAllInBatch();
	}

	@Override
	public Menu getOne(Integer id) {
		return mr.getOne(id);
	}

	@Override
	public <S extends Menu, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return mr.findBy(example, queryFunction);
	}

	@Override
	public long count() {
		return mr.count();
	}

	@Override
	public void deleteById(Integer id) {
		mr.deleteById(id);
	}

	@Override
	public Menu getById(Integer id) {
		return mr.getById(id);
	}

	@Override
	public void delete(Menu entity) {
		mr.delete(entity);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		mr.deleteAllById(ids);
	}

	@Override
	public Menu getReferenceById(Integer id) {
		return mr.getReferenceById(id);
	}

	@Override
	public void deleteAll(Iterable<? extends Menu> entities) {
		mr.deleteAll(entities);
	}

	@Override
	public <S extends Menu> List<S> findAll(Example<S> example) {
		return mr.findAll(example);
	}

	@Override
	public <S extends Menu> List<S> findAll(Example<S> example, Sort sort) {
		return mr.findAll(example, sort);
	}

	@Override
	public void deleteAll() {
		mr.deleteAll();
	}
	
	
}
