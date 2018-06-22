package cn.fly.test;

import java.util.List;

import org.elasticsearch.client.Client;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.fly.domain.Article;
import cn.fly.service.ArticleService;

@RunWith(value=SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class ArticleServiceTest {

	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private Client client;
	
	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;
	
	//创建索引和映射
	@Test
	public void createIndex(){
		elasticsearchTemplate.createIndex(Article.class);
		elasticsearchTemplate.putMapping(Article.class);
//		articleService.createIndex(Article.class);
	}
	
	//保存
	@Test
	public void save(){
		Article article = new Article();
		article.setId(1);
		article.setTitle("ElasticSearch是一个基于Lucene的搜索服务器,帮助我们实现全文检索功能框架");
		article.setContent("2Elasticsearch是用Java开发的，并作为Apache许可条款下的开放源码发布，是当前流行的企业级搜索引擎。");
		
		articleService.save(article);
	}
	
	//更新
	@Test
	public void update(){
		Article article = new Article();
		article.setId(1);
		article.setTitle("11ElasticSearch是一个基于Lucene的搜索服务器,帮助我们实现全文检索功能框架");
		article.setContent("11Elasticsearch是用Java开发的，并作为Apache许可条款下的开放源码发布，是当前流行的企业级搜索引擎。");
		
		articleService.save(article);
	}
	
	//查询
	@Test
	public void findOne(){
		Article article = articleService.findOne(1);
		System.out.println(article);
	}
	
	//查询所有
	@Test
	public void findAll(){
		Iterable<Article> list =  articleService.findAll();
		for (Article article : list) {
			System.out.println(article);
		}
	}
	
	@Test
	public void save100(){
		for(int i=1;i<=100;i++){
			Article article = new Article();
			article.setId(i);
			article.setTitle(i+"ElasticSearch是一个基于Lucene的搜索服务器,帮助我们实现全文检索功能框架");
			article.setContent(i+"Elasticsearch是用Java开发的，并作为Apache许可条款下的开放源码发布，是当前流行的企业级搜索引擎。");
			articleService.save(article);
			System.out.println(i);
		}
	}
	
	//分页查询
	@Test
	public void findAllPage(){
		Pageable pageable = new PageRequest(0, 20,new Sort(Direction.ASC,"id"));//排序
		Page<Article> page = articleService.findAll(pageable);
		System.out.println("总记录数："+page.getTotalElements());
		for (Article article : page) {
			System.out.println(article);
		}
	}
	
	//删除
	@Test
	public void delete(){
		Article article = new Article();
		article.setId(1);
		articleService.delete(article);
	}
	
	//条件查询
	@Test
	public void findByTitle(){
		String title = "搜索";
		List<Article> list = articleService.findByTitle(title);
		for (Article article : list) {
			System.out.println(article);
		}
	}
	
	//分页 条件查询 排序
	@Test
	public void findByCondition(){
		String title = "搜索";
		Pageable pageable = new PageRequest(0, 20, new Sort(Direction.ASC,"id"));
		Page<Article> page = articleService.findByTitle(title,pageable);
		System.out.println("总记录数："+page.getTotalElements());
		for (Article article : page) {
			System.out.println(article);
		}
	}
}
