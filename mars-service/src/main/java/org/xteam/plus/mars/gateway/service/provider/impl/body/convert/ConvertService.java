package org.xteam.plus.mars.gateway.service.provider.impl.body.convert;

public interface ConvertService<PO, VO> {

    /**
     * 对象转换 po 转换 vo
     *
     * @param po
     * @return
     */
    public VO toVO(PO po);

    /**
     * 对象转换 VO 转换 po
     *
     * @param vo
     * @return
     */
    public PO toPO(VO vo);
}
