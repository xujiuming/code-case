package com.onlyedu.sell.contract.po;

import java.util.Collection;


/**脚本自动生成 mapper
 *
 * @author ming
 * @date 2018-06-30 22:24:03
 */
public interface  ClassWalletDraftMapper{

    int insert(ClassWalletDraft t);

    int insertAll(Collection<ClassWalletDraft> ts);

    int update(ClassWalletDraft t);

    ClassWalletDraft findById(Integer id);

    Collection<ClassWalletDraft> findAll();

    int deleteById(Integer id);

}