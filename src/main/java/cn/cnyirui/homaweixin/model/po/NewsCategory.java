package cn.cnyirui.homaweixin.model.po;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import cn.cnyirui.framework.model.po.base.StandardSetupEntity;

/**
 * The persistent class for the news_category database table.标准设置_企业资讯分类
 * 
 */
@Entity
@Table(name = "news_category")
public class NewsCategory extends StandardSetupEntity {
	private static final long serialVersionUID = 1L;

	/**
	 * 企业资讯
	 */
	// bi-directional many-to-one association to New
	@OneToMany(mappedBy = "category")
	private List<News> newsList;

	/**
	 * 企业资讯
	 * 
	 * @return newsList 企业资讯
	 */
	public List<News> getNewsList() {
		return newsList;
	}

	/**
	 * 企业资讯
	 * 
	 * @param newsList
	 *            企业资讯
	 */
	public void setNewsList(List<News> newsList) {
		this.newsList = newsList;
	}

	public News addNewsList(News newsList) {
		getNewsList().add(newsList);
		newsList.setCategory(this);

		return newsList;
	}

	public News removeNewsList(News newsList) {
		getNewsList().remove(newsList);
		newsList.setCategory(null);

		return newsList;
	}
}
