package com.zhangqie.module_mine.adapters;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.zhangqie.base.base_api.res_data.RankBean;
import com.zhangqie.module_mine.R;

import org.jetbrains.annotations.NotNull;

public class RvAdapterScoreRankList extends BaseQuickAdapter<RankBean, BaseViewHolder> {
    public RvAdapterScoreRankList() {
        super(R.layout.rv_item_score_rank_list);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, RankBean rankListRes) {
        String rank = rankListRes.getRank();
        baseViewHolder.setGone(R.id.ivLogo, (!rank.equals("1") && !rank.equals("2") && !rank.equals("3")))
                .setImageResource(R.id.ivLogo, rank.equals("1") ? R.mipmap.gold_crown : (rank.equals("2") ? R.mipmap.silver_crown : R.mipmap.cooper_crown))
                .setGone(R.id.tvLogo, (rank.equals("1") || rank.equals("2") || rank.equals("3")))
                .setText(R.id.tvLogo, rank)
                .setText(R.id.tvName, rankListRes.getUsername())
                .setText(R.id.tvCoins, rankListRes.getCoinCount() + "");

    }
}
