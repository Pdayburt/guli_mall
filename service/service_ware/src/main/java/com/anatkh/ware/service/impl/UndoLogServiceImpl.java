package com.anatkh.ware.service.impl;

import com.anatkh.ware.entity.UndoLog;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.anatkh.ware.service.UndoLogService;
import com.anatkh.ware.mapper.UndoLogMapper;
import org.springframework.stereotype.Service;

/**
* @author anatkh
* @description 针对表【undo_log】的数据库操作Service实现
* @createDate 2022-12-13 10:59:10
*/
@Service
public class UndoLogServiceImpl extends ServiceImpl<UndoLogMapper, UndoLog>
    implements UndoLogService{

}




