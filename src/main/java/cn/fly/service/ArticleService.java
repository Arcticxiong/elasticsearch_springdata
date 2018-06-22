package cn.fly.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.fly.domain.Article;

public interface ArticleService {
	
	public void save(Article article);

	public Article findOne(int i);

	public Iterable<Article> findAll();

	public Page<Article> findAll(Pageable pageable);

	public void delete(Article article);

	public List<Article> findByTitle(String title);

	public Page<Article> findByTitle(String title, Pageable pageable);

	public void createIndex(Class<Article> class1);
}
