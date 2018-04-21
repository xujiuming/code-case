package ${packageName}

${importNameList}

/** ${remarks}
 *
 * @author ${authorName}
 * @date ${date}
 */
public interface  ${className}{

    insert(${pojoClass} t);

    insertAll(${pojoClassAll} ts);

    update(${pojoClass} t);
    updateAll(${pojoClassAll} ts);

    findById(${idClass} id);

    findAll();

    deleteById(${idClass} id);

}