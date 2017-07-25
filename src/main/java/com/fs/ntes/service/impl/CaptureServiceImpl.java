package com.fs.ntes.service.impl;

import com.fs.dreams.Artist;
import com.fs.dreams.CaptureUtils;
import com.fs.dreams.mapper.ArtistMapper;
import com.fs.ntes.service.CaptureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author fengsong
 * @description:一句话描述下类的功能
 * @time 2017-07-12 18:59
 **/
@Service
public class CaptureServiceImpl implements CaptureService {
    @Autowired
    private ArtistMapper artistMapper;

    @Override
    public boolean captureArtist(String artistName) {

        List<Artist> artists= CaptureUtils.searchArtist(artistName,1,1);
        artistMapper.insert(artists.get(0));
        return false;
    }
}
