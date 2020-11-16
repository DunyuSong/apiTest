package com.api.test.dao;

import com.api.test.entity.TestCaseEntity;

import java.util.List;

public interface TestCaseDao {
     List<TestCaseEntity>  queryAll();
     List<String> queryCaseName();
     boolean addCase(TestCaseEntity testCaseEntity);
     boolean updateById(TestCaseEntity testCaseEntity);
     boolean deleteByCaseId(TestCaseEntity testCaseEntity);
     List<TestCaseEntity> queryByCaseId(TestCaseEntity testCaseEntity);
     int count(TestCaseEntity testCaseEntity);
     List<TestCaseEntity> queryByDetail(TestCaseEntity testCaseEntity);
     int queryIdByCaseName(String caseName);
     boolean updateCaseIdById(String caseId, int id);
     boolean updateIsDeleteById(TestCaseEntity testCaseEntity);

}
