package com.app.alcohol.dao;

import com.app.alcohol.entity.NBackRecord;
import com.app.alcohol.entity.SSTRecord;
import com.app.alcohol.vo.MyRankVO;
import com.app.alcohol.vo.SSTInfoVO;
import com.app.alcohol.vo.SortElementVO;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SSTRecordMapper extends BaseMapper<SSTRecord> {
    List<SSTInfoVO> selectLatestSSTRecord(@Param("username") String username, @Param("bound") int bound);
    List<SortElementVO> getReactionTimeRankInfo();
    MyRankVO getMyReactionTimeRank(@Param("username") String username);
    List<SortElementVO> getStopSignalRankInfo();
    MyRankVO getMyStopSignalRank(@Param("username") String username);
    List<SortElementVO> getGoStimuliRankInfo();
    MyRankVO getMyGoStimuliRank(@Param("username") String username);


}
