package com.fs.dreams.service.impl;

import com.fs.dreams.Artist;
import com.fs.dreams.CaptureUtils;
import com.fs.dreams.dto.FPageResponse;
import com.fs.dreams.mapper.ArtistMapperExt;
import com.fs.dreams.service.CaptureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author fengsong
 * @description:一句话描述下类的功能
 * @time 2017-07-12 18:59
 **/
@Service
public class CaptureServiceImpl implements CaptureService {

    @Autowired
    private ArtistMapperExt artistMapperExt;

    @Override
    public boolean captureArtist(String artistName) {

        FPageResponse<Artist> pageResponse = CaptureUtils.searchArtist(artistName, 1, 1);
        artistMapperExt.batchInsert(pageResponse.getDataList());
        return false;
    }
}
