package com.drawint.domain.entity;

import java.util.ArrayList;
import java.util.List;

public class TerminalActionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TerminalActionExample() {
        oredCriteria = new ArrayList<>();
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
            criteria = new ArrayList<>();
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andTmIdIsNull() {
            addCriterion("tm_id is null");
            return (Criteria) this;
        }

        public Criteria andTmIdIsNotNull() {
            addCriterion("tm_id is not null");
            return (Criteria) this;
        }

        public Criteria andTmIdEqualTo(Long value) {
            addCriterion("tm_id =", value, "tmId");
            return (Criteria) this;
        }

        public Criteria andTmIdNotEqualTo(Long value) {
            addCriterion("tm_id <>", value, "tmId");
            return (Criteria) this;
        }

        public Criteria andTmIdGreaterThan(Long value) {
            addCriterion("tm_id >", value, "tmId");
            return (Criteria) this;
        }

        public Criteria andTmIdGreaterThanOrEqualTo(Long value) {
            addCriterion("tm_id >=", value, "tmId");
            return (Criteria) this;
        }

        public Criteria andTmIdLessThan(Long value) {
            addCriterion("tm_id <", value, "tmId");
            return (Criteria) this;
        }

        public Criteria andTmIdLessThanOrEqualTo(Long value) {
            addCriterion("tm_id <=", value, "tmId");
            return (Criteria) this;
        }

        public Criteria andTmIdIn(List<Long> values) {
            addCriterion("tm_id in", values, "tmId");
            return (Criteria) this;
        }

        public Criteria andTmIdNotIn(List<Long> values) {
            addCriterion("tm_id not in", values, "tmId");
            return (Criteria) this;
        }

        public Criteria andTmIdBetween(Long value1, Long value2) {
            addCriterion("tm_id between", value1, value2, "tmId");
            return (Criteria) this;
        }

        public Criteria andTmIdNotBetween(Long value1, Long value2) {
            addCriterion("tm_id not between", value1, value2, "tmId");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andCodeIsNull() {
            addCriterion("code is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("code is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(String value) {
            addCriterion("code =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(String value) {
            addCriterion("code <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(String value) {
            addCriterion("code >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(String value) {
            addCriterion("code >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(String value) {
            addCriterion("code <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(String value) {
            addCriterion("code <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLike(String value) {
            addCriterion("code like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotLike(String value) {
            addCriterion("code not like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<String> values) {
            addCriterion("code in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<String> values) {
            addCriterion("code not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(String value1, String value2) {
            addCriterion("code between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(String value1, String value2) {
            addCriterion("code not between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andConcurrencyLevelIsNull() {
            addCriterion("concurrency_level is null");
            return (Criteria) this;
        }

        public Criteria andConcurrencyLevelIsNotNull() {
            addCriterion("concurrency_level is not null");
            return (Criteria) this;
        }

        public Criteria andConcurrencyLevelEqualTo(Integer value) {
            addCriterion("concurrency_level =", value, "concurrencyLevel");
            return (Criteria) this;
        }

        public Criteria andConcurrencyLevelNotEqualTo(Integer value) {
            addCriterion("concurrency_level <>", value, "concurrencyLevel");
            return (Criteria) this;
        }

        public Criteria andConcurrencyLevelGreaterThan(Integer value) {
            addCriterion("concurrency_level >", value, "concurrencyLevel");
            return (Criteria) this;
        }

        public Criteria andConcurrencyLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("concurrency_level >=", value, "concurrencyLevel");
            return (Criteria) this;
        }

        public Criteria andConcurrencyLevelLessThan(Integer value) {
            addCriterion("concurrency_level <", value, "concurrencyLevel");
            return (Criteria) this;
        }

        public Criteria andConcurrencyLevelLessThanOrEqualTo(Integer value) {
            addCriterion("concurrency_level <=", value, "concurrencyLevel");
            return (Criteria) this;
        }

        public Criteria andConcurrencyLevelIn(List<Integer> values) {
            addCriterion("concurrency_level in", values, "concurrencyLevel");
            return (Criteria) this;
        }

        public Criteria andConcurrencyLevelNotIn(List<Integer> values) {
            addCriterion("concurrency_level not in", values, "concurrencyLevel");
            return (Criteria) this;
        }

        public Criteria andConcurrencyLevelBetween(Integer value1, Integer value2) {
            addCriterion("concurrency_level between", value1, value2, "concurrencyLevel");
            return (Criteria) this;
        }

        public Criteria andConcurrencyLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("concurrency_level not between", value1, value2, "concurrencyLevel");
            return (Criteria) this;
        }

        public Criteria andAroundIntervalIsNull() {
            addCriterion("around_interval is null");
            return (Criteria) this;
        }

        public Criteria andAroundIntervalIsNotNull() {
            addCriterion("around_interval is not null");
            return (Criteria) this;
        }

        public Criteria andAroundIntervalEqualTo(Integer value) {
            addCriterion("around_interval =", value, "aroundInterval");
            return (Criteria) this;
        }

        public Criteria andAroundIntervalNotEqualTo(Integer value) {
            addCriterion("around_interval <>", value, "aroundInterval");
            return (Criteria) this;
        }

        public Criteria andAroundIntervalGreaterThan(Integer value) {
            addCriterion("around_interval >", value, "aroundInterval");
            return (Criteria) this;
        }

        public Criteria andAroundIntervalGreaterThanOrEqualTo(Integer value) {
            addCriterion("around_interval >=", value, "aroundInterval");
            return (Criteria) this;
        }

        public Criteria andAroundIntervalLessThan(Integer value) {
            addCriterion("around_interval <", value, "aroundInterval");
            return (Criteria) this;
        }

        public Criteria andAroundIntervalLessThanOrEqualTo(Integer value) {
            addCriterion("around_interval <=", value, "aroundInterval");
            return (Criteria) this;
        }

        public Criteria andAroundIntervalIn(List<Integer> values) {
            addCriterion("around_interval in", values, "aroundInterval");
            return (Criteria) this;
        }

        public Criteria andAroundIntervalNotIn(List<Integer> values) {
            addCriterion("around_interval not in", values, "aroundInterval");
            return (Criteria) this;
        }

        public Criteria andAroundIntervalBetween(Integer value1, Integer value2) {
            addCriterion("around_interval between", value1, value2, "aroundInterval");
            return (Criteria) this;
        }

        public Criteria andAroundIntervalNotBetween(Integer value1, Integer value2) {
            addCriterion("around_interval not between", value1, value2, "aroundInterval");
            return (Criteria) this;
        }

        public Criteria andIsDeletedIsNull() {
            addCriterion("is_deleted is null");
            return (Criteria) this;
        }

        public Criteria andIsDeletedIsNotNull() {
            addCriterion("is_deleted is not null");
            return (Criteria) this;
        }

        public Criteria andIsDeletedEqualTo(Boolean value) {
            addCriterion("is_deleted =", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotEqualTo(Boolean value) {
            addCriterion("is_deleted <>", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedGreaterThan(Boolean value) {
            addCriterion("is_deleted >", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_deleted >=", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedLessThan(Boolean value) {
            addCriterion("is_deleted <", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedLessThanOrEqualTo(Boolean value) {
            addCriterion("is_deleted <=", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedIn(List<Boolean> values) {
            addCriterion("is_deleted in", values, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotIn(List<Boolean> values) {
            addCriterion("is_deleted not in", values, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedBetween(Boolean value1, Boolean value2) {
            addCriterion("is_deleted between", value1, value2, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_deleted not between", value1, value2, "isDeleted");
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