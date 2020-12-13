package cn.ztuo.bitrade.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
public class Waiter
{
    private String name;
    private String qrUrl;
    private String qrDownloadUrl;
    private String qrAzDownloadUrl;
}
