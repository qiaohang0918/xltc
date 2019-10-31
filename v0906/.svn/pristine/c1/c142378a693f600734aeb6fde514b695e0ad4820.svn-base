/**
 * 
 */
package com.qigan.qiganshop.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qigan.qiganshop.constant.DataState;
import com.qigan.qiganshop.domain.TbLocalCategory;
import com.qigan.qiganshop.domain.TbLocalCategoryExample;
import com.qigan.qiganshop.exception.XltcRuntimeException;
import com.qigan.qiganshop.mapper.CategoryMapper;
import com.qigan.qiganshop.mapper.LabelMapper;
import com.qigan.qiganshop.mapper.TbLocalCategoryMapper;
import com.qigan.qiganshop.pojo.Category;
import com.qigan.qiganshop.pojo.XltcGoodsLabelModel;
import com.qigan.qiganshop.service.LabelService;
import com.qigan.qiganshop.service.TbLocalCategoryService;
import com.qigan.qiganshop.util.notnull.NotNull;

/**
 * @author wyy
 *
 */
@Service
@Transactional
public class TbLocalCategoryServiceImpl implements TbLocalCategoryService {

	@Autowired
	private TbLocalCategoryMapper cateMapper;

	@Autowired
	private CategoryMapper mapper;

	@Autowired
	private LabelMapper labelMapper;

	@Autowired
	private LabelService labelService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.qigan.qiganshop.service.TbLocalCategoryService#findAllLocalCate()
	 */
	@Override
	public List<?> findAllLocalCate() {
		// TODO Auto-generated method stub
		return cateMapper.selectByExample(null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.qigan.qiganshop.service.TbLocalCategoryService#
	 * findAllLocalCateByEnable(java.lang.String)
	 */
	@Override
	public List<?> findAllLocalCateByEnable(String enable) {
		// TODO Auto-generated method stub
		if (StringUtils.isBlank(enable))
			throw XltcRuntimeException.throwable("参数不完整，请检查参数");

		TbLocalCategoryExample example = new TbLocalCategoryExample();
		example.createCriteria().andTypeEnableEqualTo(enable);
		return cateMapper.selectByExample(example);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.qigan.qiganshop.service.TbLocalCategoryService#findOne(java.lang.
	 * Long)
	 */
	@Override
	public TbLocalCategory findOne(Long id) {
		// TODO Auto-generated method stub
		if (id == null)
			throw XltcRuntimeException.throwable("参数不完整，请检查参数");

		return cateMapper.selectByPrimaryKey(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.qigan.qiganshop.service.TbLocalCategoryService#findOneByEnable(java.
	 * lang.Long, java.lang.String)
	 */
	@Override
	public TbLocalCategory findOneByEnable(Long id, String enable) {
		// TODO Auto-generated method stub
		if (id == null || StringUtils.isBlank(enable))
			throw XltcRuntimeException.throwable("参数不完整，请检查参数");

		return cateMapper.selectByPrimaryKeyAndEnable(id, enable);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.qigan.qiganshop.service.TbLocalCategoryService#save(com.qigan.
	 * qiganshop.domain.TbLocalCategory)
	 */
	@Override
	public void save(TbLocalCategory model) {
		// TODO Auto-generated method stub
		if (model == null)
			throw XltcRuntimeException.throwable("参数不完整，请检查参数");
		if(StringUtils.isAllBlank(model.getTypeName()))
			throw XltcRuntimeException.throwable("参数不完整，请检查参数");
		cateMapper.insertSelective(model);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.qigan.qiganshop.service.TbLocalCategoryService#update(com.qigan.
	 * qiganshop.domain.TbLocalCategory)
	 */
	@Override
	public void update(TbLocalCategory model) {
		// TODO Auto-generated method stub
		if (model == null)
			throw XltcRuntimeException.throwable("参数不完整，请检查参数");
		cateMapper.updateByPrimaryKeySelective(model);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.qigan.qiganshop.service.TbLocalCategoryService#delete(java.lang.Long[
	 * ])
	 */
	@Override
	public void delete(Long... ids) {
		// TODO Auto-generated method stub
		if (ids == null)
			throw XltcRuntimeException.throwable("参数不完整，请检查参数");
		for (Long id : ids){
			List<XltcGoodsLabelModel> l = labelMapper.findLableByLocalCates(id + "");
			if(l != null && l.size() > 0)
				throw XltcRuntimeException.throwable("该分类下存在二级分类，无法删除");
			cateMapper.deleteByPrimaryKey(id);
		}
			
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.qigan.qiganshop.service.TbLocalCategoryService#enable(java.lang.Long[
	 * ])
	 */
	@Override
	public void enable(String enable, Long... ids) {
		// TODO Auto-generated method stub
		if (ids == null || StringUtils.isBlank(enable))
			throw XltcRuntimeException.throwable("参数不完整，请检查参数");
		for (Long id : ids) {
			TbLocalCategory model = new TbLocalCategory();
			model.setId(id);
			model.setTypeEnable(enable);
			cateMapper.updateByPrimaryKeySelective(model);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.qigan.qiganshop.service.TbLocalCategoryService#sort(int,
	 * java.lang.Long)
	 */
	@Override
	public void sort(int sort, Long id) {
		// TODO Auto-generated method stub
		if (id == null)
			throw XltcRuntimeException.throwable("参数不完整，请检查参数");
		TbLocalCategory model = new TbLocalCategory();
		model.setId(id);
		model.setTypeSort(sort);
		cateMapper.updateByPrimaryKeySelective(model);
	}

	@Override
	public List<Category> findAll2Category(String enable) {
		// TODO Auto-generated method stub
		enable = StringUtils.isBlank(enable) ? DataState.NOR : enable;
		return cateMapper.selectAll2Category(enable);
	}

	@Override
	public List<?> findAllCate() {
		// TODO Auto-generated method stub
		List<Category> list = (List<Category>) NotNull.checkListNull(mapper.findByCategory(null, null, null));
		List<Category> localList = cateMapper.selectAll2Categorys();
		if (localList != null && localList.size() > 0) 
			list.addAll(localList);

		for (Category cate : list) {
			if (cate != null) {
				if (cate.isChangeInterface()) {
					List<XltcGoodsLabelModel> lR = labelMapper.findLableByLocalCate(cate.getCateId());
					filterAndSetChild(cate, lR);
					continue;
				}
				List<XltcGoodsLabelModel> rows = labelMapper.findLabelByCate(cate.getCateId());
				filterAndSetChild(cate, rows);
			}
		}
		list = list.stream().sorted(Comparator.comparing(Category::getSort)).collect(Collectors.toList());
		return list;
	}
	
	private Category filterAndSetChild(Category cate, List<XltcGoodsLabelModel> rows){
		if(rows != null && rows.size() > 0)
			rows = rows.stream().filter(x -> StringUtils.isNotBlank(x.getLabelId())).collect(Collectors.toList());
		
		if(rows != null && rows.size() > 0)
			cate.setHasChild(true);
		
		cate.setLabels(rows);
		return cate;
	}

	@Override
	public Category findOneById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
