package com.fs.dreams.mapper;

import com.fs.dreams.Artist;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author fengsong
 * @description:一句话描述下类的功能
 * @time 2017-08-01 16:56
 **/
@Mapper
public interface ArtistMapperExt extends ArtistMapper{

    int batchInsert(List<Artist> artists);

}
