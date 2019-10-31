package com.qigan.qiganshop.pojo;

import java.util.ArrayList;
import java.util.List;

public class TbActivityMainExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbActivityMainExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andActivityIdIsNull() {
            addCriterion("activity_id is null");
            return (Criteria) this;
        }

        public Criteria andActivityIdIsNotNull() {
            addCriterion("activity_id is not null");
            return (Criteria) this;
        }

        public Criteria andActivityIdEqualTo(String value) {
            addCriterion("activity_id =", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdNotEqualTo(String value) {
            addCriterion("activity_id <>", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdGreaterThan(String value) {
            addCriterion("activity_id >", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdGreaterThanOrEqualTo(String value) {
            addCriterion("activity_id >=", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdLessThan(String value) {
            addCriterion("activity_id <", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdLessThanOrEqualTo(String value) {
            addCriterion("activity_id <=", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdLike(String value) {
            addCriterion("activity_id like", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdNotLike(String value) {
            addCriterion("activity_id not like", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdIn(List<String> values) {
            addCriterion("activity_id in", values, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdNotIn(List<String> values) {
            addCriterion("activity_id not in", values, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdBetween(String value1, String value2) {
            addCriterion("activity_id between", value1, value2, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdNotBetween(String value1, String value2) {
            addCriterion("activity_id not between", value1, value2, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityNameIsNull() {
            addCriterion("activity_name is null");
            return (Criteria) this;
        }

        public Criteria andActivityNameIsNotNull() {
            addCriterion("activity_name is not null");
            return (Criteria) this;
        }

        public Criteria andActivityNameEqualTo(String value) {
            addCriterion("activity_name =", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameNotEqualTo(String value) {
            addCriterion("activity_name <>", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameGreaterThan(String value) {
            addCriterion("activity_name >", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameGreaterThanOrEqualTo(String value) {
            addCriterion("activity_name >=", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameLessThan(String value) {
            addCriterion("activity_name <", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameLessThanOrEqualTo(String value) {
            addCriterion("activity_name <=", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameLike(String value) {
            addCriterion("activity_name like", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameNotLike(String value) {
            addCriterion("activity_name not like", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameIn(List<String> values) {
            addCriterion("activity_name in", values, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameNotIn(List<String> values) {
            addCriterion("activity_name not in", values, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameBetween(String value1, String value2) {
            addCriterion("activity_name between", value1, value2, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameNotBetween(String value1, String value2) {
            addCriterion("activity_name not between", value1, value2, "activityName");
            return (Criteria) this;
        }

        public Criteria andMainPictureIsNull() {
            addCriterion("main_picture is null");
            return (Criteria) this;
        }

        public Criteria andMainPictureIsNotNull() {
            addCriterion("main_picture is not null");
            return (Criteria) this;
        }

        public Criteria andMainPictureEqualTo(String value) {
            addCriterion("main_picture =", value, "mainPicture");
            return (Criteria) this;
        }

        public Criteria andMainPictureNotEqualTo(String value) {
            addCriterion("main_picture <>", value, "mainPicture");
            return (Criteria) this;
        }

        public Criteria andMainPictureGreaterThan(String value) {
            addCriterion("main_picture >", value, "mainPicture");
            return (Criteria) this;
        }

        public Criteria andMainPictureGreaterThanOrEqualTo(String value) {
            addCriterion("main_picture >=", value, "mainPicture");
            return (Criteria) this;
        }

        public Criteria andMainPictureLessThan(String value) {
            addCriterion("main_picture <", value, "mainPicture");
            return (Criteria) this;
        }

        public Criteria andMainPictureLessThanOrEqualTo(String value) {
            addCriterion("main_picture <=", value, "mainPicture");
            return (Criteria) this;
        }

        public Criteria andMainPictureLike(String value) {
            addCriterion("main_picture like", value, "mainPicture");
            return (Criteria) this;
        }

        public Criteria andMainPictureNotLike(String value) {
            addCriterion("main_picture not like", value, "mainPicture");
            return (Criteria) this;
        }

        public Criteria andMainPictureIn(List<String> values) {
            addCriterion("main_picture in", values, "mainPicture");
            return (Criteria) this;
        }

        public Criteria andMainPictureNotIn(List<String> values) {
            addCriterion("main_picture not in", values, "mainPicture");
            return (Criteria) this;
        }

        public Criteria andMainPictureBetween(String value1, String value2) {
            addCriterion("main_picture between", value1, value2, "mainPicture");
            return (Criteria) this;
        }

        public Criteria andMainPictureNotBetween(String value1, String value2) {
            addCriterion("main_picture not between", value1, value2, "mainPicture");
            return (Criteria) this;
        }

        public Criteria andActivityStatusIsNull() {
            addCriterion("activity_status is null");
            return (Criteria) this;
        }

        public Criteria andActivityStatusIsNotNull() {
            addCriterion("activity_status is not null");
            return (Criteria) this;
        }

        public Criteria andActivityStatusEqualTo(String value) {
            addCriterion("activity_status =", value, "activityStatus");
            return (Criteria) this;
        }

        public Criteria andActivityStatusNotEqualTo(String value) {
            addCriterion("activity_status <>", value, "activityStatus");
            return (Criteria) this;
        }

        public Criteria andActivityStatusGreaterThan(String value) {
            addCriterion("activity_status >", value, "activityStatus");
            return (Criteria) this;
        }

        public Criteria andActivityStatusGreaterThanOrEqualTo(String value) {
            addCriterion("activity_status >=", value, "activityStatus");
            return (Criteria) this;
        }

        public Criteria andActivityStatusLessThan(String value) {
            addCriterion("activity_status <", value, "activityStatus");
            return (Criteria) this;
        }

        public Criteria andActivityStatusLessThanOrEqualTo(String value) {
            addCriterion("activity_status <=", value, "activityStatus");
            return (Criteria) this;
        }

        public Criteria andActivityStatusLike(String value) {
            addCriterion("activity_status like", value, "activityStatus");
            return (Criteria) this;
        }

        public Criteria andActivityStatusNotLike(String value) {
            addCriterion("activity_status not like", value, "activityStatus");
            return (Criteria) this;
        }

        public Criteria andActivityStatusIn(List<String> values) {
            addCriterion("activity_status in", values, "activityStatus");
            return (Criteria) this;
        }

        public Criteria andActivityStatusNotIn(List<String> values) {
            addCriterion("activity_status not in", values, "activityStatus");
            return (Criteria) this;
        }

        public Criteria andActivityStatusBetween(String value1, String value2) {
            addCriterion("activity_status between", value1, value2, "activityStatus");
            return (Criteria) this;
        }

        public Criteria andActivityStatusNotBetween(String value1, String value2) {
            addCriterion("activity_status not between", value1, value2, "activityStatus");
            return (Criteria) this;
        }

        public Criteria andActactivitySortIsNull() {
            addCriterion("actactivity_sort is null");
            return (Criteria) this;
        }

        public Criteria andActactivitySortIsNotNull() {
            addCriterion("actactivity_sort is not null");
            return (Criteria) this;
        }

        public Criteria andActactivitySortEqualTo(String value) {
            addCriterion("actactivity_sort =", value, "actactivitySort");
            return (Criteria) this;
        }

        public Criteria andActactivitySortNotEqualTo(String value) {
            addCriterion("actactivity_sort <>", value, "actactivitySort");
            return (Criteria) this;
        }

        public Criteria andActactivitySortGreaterThan(String value) {
            addCriterion("actactivity_sort >", value, "actactivitySort");
            return (Criteria) this;
        }

        public Criteria andActactivitySortGreaterThanOrEqualTo(String value) {
            addCriterion("actactivity_sort >=", value, "actactivitySort");
            return (Criteria) this;
        }

        public Criteria andActactivitySortLessThan(String value) {
            addCriterion("actactivity_sort <", value, "actactivitySort");
            return (Criteria) this;
        }

        public Criteria andActactivitySortLessThanOrEqualTo(String value) {
            addCriterion("actactivity_sort <=", value, "actactivitySort");
            return (Criteria) this;
        }

        public Criteria andActactivitySortLike(String value) {
            addCriterion("actactivity_sort like", value, "actactivitySort");
            return (Criteria) this;
        }

        public Criteria andActactivitySortNotLike(String value) {
            addCriterion("actactivity_sort not like", value, "actactivitySort");
            return (Criteria) this;
        }

        public Criteria andActactivitySortIn(List<String> values) {
            addCriterion("actactivity_sort in", values, "actactivitySort");
            return (Criteria) this;
        }

        public Criteria andActactivitySortNotIn(List<String> values) {
            addCriterion("actactivity_sort not in", values, "actactivitySort");
            return (Criteria) this;
        }

        public Criteria andActactivitySortBetween(String value1, String value2) {
            addCriterion("actactivity_sort between", value1, value2, "actactivitySort");
            return (Criteria) this;
        }

        public Criteria andActactivitySortNotBetween(String value1, String value2) {
            addCriterion("actactivity_sort not between", value1, value2, "actactivitySort");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(String value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(String value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(String value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(String value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(String value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(String value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLike(String value) {
            addCriterion("create_time like", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotLike(String value) {
            addCriterion("create_time not like", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<String> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<String> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(String value1, String value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(String value1, String value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andActivitySkipUrlIsNull() {
            addCriterion("activity_skip_url is null");
            return (Criteria) this;
        }

        public Criteria andActivitySkipUrlIsNotNull() {
            addCriterion("activity_skip_url is not null");
            return (Criteria) this;
        }

        public Criteria andActivitySkipUrlEqualTo(String value) {
            addCriterion("activity_skip_url =", value, "activitySkipUrl");
            return (Criteria) this;
        }

        public Criteria andActivitySkipUrlNotEqualTo(String value) {
            addCriterion("activity_skip_url <>", value, "activitySkipUrl");
            return (Criteria) this;
        }

        public Criteria andActivitySkipUrlGreaterThan(String value) {
            addCriterion("activity_skip_url >", value, "activitySkipUrl");
            return (Criteria) this;
        }

        public Criteria andActivitySkipUrlGreaterThanOrEqualTo(String value) {
            addCriterion("activity_skip_url >=", value, "activitySkipUrl");
            return (Criteria) this;
        }

        public Criteria andActivitySkipUrlLessThan(String value) {
            addCriterion("activity_skip_url <", value, "activitySkipUrl");
            return (Criteria) this;
        }

        public Criteria andActivitySkipUrlLessThanOrEqualTo(String value) {
            addCriterion("activity_skip_url <=", value, "activitySkipUrl");
            return (Criteria) this;
        }

        public Criteria andActivitySkipUrlLike(String value) {
            addCriterion("activity_skip_url like", value, "activitySkipUrl");
            return (Criteria) this;
        }

        public Criteria andActivitySkipUrlNotLike(String value) {
            addCriterion("activity_skip_url not like", value, "activitySkipUrl");
            return (Criteria) this;
        }

        public Criteria andActivitySkipUrlIn(List<String> values) {
            addCriterion("activity_skip_url in", values, "activitySkipUrl");
            return (Criteria) this;
        }

        public Criteria andActivitySkipUrlNotIn(List<String> values) {
            addCriterion("activity_skip_url not in", values, "activitySkipUrl");
            return (Criteria) this;
        }

        public Criteria andActivitySkipUrlBetween(String value1, String value2) {
            addCriterion("activity_skip_url between", value1, value2, "activitySkipUrl");
            return (Criteria) this;
        }

        public Criteria andActivitySkipUrlNotBetween(String value1, String value2) {
            addCriterion("activity_skip_url not between", value1, value2, "activitySkipUrl");
            return (Criteria) this;
        }

        public Criteria andTopPictureIsNull() {
            addCriterion("top_picture is null");
            return (Criteria) this;
        }

        public Criteria andTopPictureIsNotNull() {
            addCriterion("top_picture is not null");
            return (Criteria) this;
        }

        public Criteria andTopPictureEqualTo(String value) {
            addCriterion("top_picture =", value, "topPicture");
            return (Criteria) this;
        }

        public Criteria andTopPictureNotEqualTo(String value) {
            addCriterion("top_picture <>", value, "topPicture");
            return (Criteria) this;
        }

        public Criteria andTopPictureGreaterThan(String value) {
            addCriterion("top_picture >", value, "topPicture");
            return (Criteria) this;
        }

        public Criteria andTopPictureGreaterThanOrEqualTo(String value) {
            addCriterion("top_picture >=", value, "topPicture");
            return (Criteria) this;
        }

        public Criteria andTopPictureLessThan(String value) {
            addCriterion("top_picture <", value, "topPicture");
            return (Criteria) this;
        }

        public Criteria andTopPictureLessThanOrEqualTo(String value) {
            addCriterion("top_picture <=", value, "topPicture");
            return (Criteria) this;
        }

        public Criteria andTopPictureLike(String value) {
            addCriterion("top_picture like", value, "topPicture");
            return (Criteria) this;
        }

        public Criteria andTopPictureNotLike(String value) {
            addCriterion("top_picture not like", value, "topPicture");
            return (Criteria) this;
        }

        public Criteria andTopPictureIn(List<String> values) {
            addCriterion("top_picture in", values, "topPicture");
            return (Criteria) this;
        }

        public Criteria andTopPictureNotIn(List<String> values) {
            addCriterion("top_picture not in", values, "topPicture");
            return (Criteria) this;
        }

        public Criteria andTopPictureBetween(String value1, String value2) {
            addCriterion("top_picture between", value1, value2, "topPicture");
            return (Criteria) this;
        }

        public Criteria andTopPictureNotBetween(String value1, String value2) {
            addCriterion("top_picture not between", value1, value2, "topPicture");
            return (Criteria) this;
        }

        public Criteria andBottomPictureIsNull() {
            addCriterion("bottom_picture is null");
            return (Criteria) this;
        }

        public Criteria andBottomPictureIsNotNull() {
            addCriterion("bottom_picture is not null");
            return (Criteria) this;
        }

        public Criteria andBottomPictureEqualTo(String value) {
            addCriterion("bottom_picture =", value, "bottomPicture");
            return (Criteria) this;
        }

        public Criteria andBottomPictureNotEqualTo(String value) {
            addCriterion("bottom_picture <>", value, "bottomPicture");
            return (Criteria) this;
        }

        public Criteria andBottomPictureGreaterThan(String value) {
            addCriterion("bottom_picture >", value, "bottomPicture");
            return (Criteria) this;
        }

        public Criteria andBottomPictureGreaterThanOrEqualTo(String value) {
            addCriterion("bottom_picture >=", value, "bottomPicture");
            return (Criteria) this;
        }

        public Criteria andBottomPictureLessThan(String value) {
            addCriterion("bottom_picture <", value, "bottomPicture");
            return (Criteria) this;
        }

        public Criteria andBottomPictureLessThanOrEqualTo(String value) {
            addCriterion("bottom_picture <=", value, "bottomPicture");
            return (Criteria) this;
        }

        public Criteria andBottomPictureLike(String value) {
            addCriterion("bottom_picture like", value, "bottomPicture");
            return (Criteria) this;
        }

        public Criteria andBottomPictureNotLike(String value) {
            addCriterion("bottom_picture not like", value, "bottomPicture");
            return (Criteria) this;
        }

        public Criteria andBottomPictureIn(List<String> values) {
            addCriterion("bottom_picture in", values, "bottomPicture");
            return (Criteria) this;
        }

        public Criteria andBottomPictureNotIn(List<String> values) {
            addCriterion("bottom_picture not in", values, "bottomPicture");
            return (Criteria) this;
        }

        public Criteria andBottomPictureBetween(String value1, String value2) {
            addCriterion("bottom_picture between", value1, value2, "bottomPicture");
            return (Criteria) this;
        }

        public Criteria andBottomPictureNotBetween(String value1, String value2) {
            addCriterion("bottom_picture not between", value1, value2, "bottomPicture");
            return (Criteria) this;
        }

        public Criteria andSmallPictureIsNull() {
            addCriterion("small_picture is null");
            return (Criteria) this;
        }

        public Criteria andSmallPictureIsNotNull() {
            addCriterion("small_picture is not null");
            return (Criteria) this;
        }

        public Criteria andSmallPictureEqualTo(String value) {
            addCriterion("small_picture =", value, "smallPicture");
            return (Criteria) this;
        }

        public Criteria andSmallPictureNotEqualTo(String value) {
            addCriterion("small_picture <>", value, "smallPicture");
            return (Criteria) this;
        }

        public Criteria andSmallPictureGreaterThan(String value) {
            addCriterion("small_picture >", value, "smallPicture");
            return (Criteria) this;
        }

        public Criteria andSmallPictureGreaterThanOrEqualTo(String value) {
            addCriterion("small_picture >=", value, "smallPicture");
            return (Criteria) this;
        }

        public Criteria andSmallPictureLessThan(String value) {
            addCriterion("small_picture <", value, "smallPicture");
            return (Criteria) this;
        }

        public Criteria andSmallPictureLessThanOrEqualTo(String value) {
            addCriterion("small_picture <=", value, "smallPicture");
            return (Criteria) this;
        }

        public Criteria andSmallPictureLike(String value) {
            addCriterion("small_picture like", value, "smallPicture");
            return (Criteria) this;
        }

        public Criteria andSmallPictureNotLike(String value) {
            addCriterion("small_picture not like", value, "smallPicture");
            return (Criteria) this;
        }

        public Criteria andSmallPictureIn(List<String> values) {
            addCriterion("small_picture in", values, "smallPicture");
            return (Criteria) this;
        }

        public Criteria andSmallPictureNotIn(List<String> values) {
            addCriterion("small_picture not in", values, "smallPicture");
            return (Criteria) this;
        }

        public Criteria andSmallPictureBetween(String value1, String value2) {
            addCriterion("small_picture between", value1, value2, "smallPicture");
            return (Criteria) this;
        }

        public Criteria andSmallPictureNotBetween(String value1, String value2) {
            addCriterion("small_picture not between", value1, value2, "smallPicture");
            return (Criteria) this;
        }

        public Criteria andBackgroundIsNull() {
            addCriterion("background is null");
            return (Criteria) this;
        }

        public Criteria andBackgroundIsNotNull() {
            addCriterion("background is not null");
            return (Criteria) this;
        }

        public Criteria andBackgroundEqualTo(String value) {
            addCriterion("background =", value, "background");
            return (Criteria) this;
        }

        public Criteria andBackgroundNotEqualTo(String value) {
            addCriterion("background <>", value, "background");
            return (Criteria) this;
        }

        public Criteria andBackgroundGreaterThan(String value) {
            addCriterion("background >", value, "background");
            return (Criteria) this;
        }

        public Criteria andBackgroundGreaterThanOrEqualTo(String value) {
            addCriterion("background >=", value, "background");
            return (Criteria) this;
        }

        public Criteria andBackgroundLessThan(String value) {
            addCriterion("background <", value, "background");
            return (Criteria) this;
        }

        public Criteria andBackgroundLessThanOrEqualTo(String value) {
            addCriterion("background <=", value, "background");
            return (Criteria) this;
        }

        public Criteria andBackgroundLike(String value) {
            addCriterion("background like", value, "background");
            return (Criteria) this;
        }

        public Criteria andBackgroundNotLike(String value) {
            addCriterion("background not like", value, "background");
            return (Criteria) this;
        }

        public Criteria andBackgroundIn(List<String> values) {
            addCriterion("background in", values, "background");
            return (Criteria) this;
        }

        public Criteria andBackgroundNotIn(List<String> values) {
            addCriterion("background not in", values, "background");
            return (Criteria) this;
        }

        public Criteria andBackgroundBetween(String value1, String value2) {
            addCriterion("background between", value1, value2, "background");
            return (Criteria) this;
        }

        public Criteria andBackgroundNotBetween(String value1, String value2) {
            addCriterion("background not between", value1, value2, "background");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}