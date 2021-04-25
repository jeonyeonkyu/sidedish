import styled from 'styled-components';

import loadingImage from '../../images/loading.gif';
import SpecialLabelTag from './SpecialLabelTag';

import ResponsiveCard from './Cards/ResponsiveCard.js';

const Card = ({ type = "default", number }) => {

  switch (type) {
    case "responsive":
      return <ResponsiveCard number={number} loadingImage={loadingImage} SpecialLabelTag />
    case "default":
      break; // 수정예정
    default:
      break;
  }

  return (
    <CardWrapper>
      <ImageWrapper>
        <Image src={loadingImage} alt="" />
        <Overlay>
          <OverlayText>
            <div>새벽배송</div>
            <hr />
            <div>전국택배</div>
          </OverlayText>
        </Overlay>
      </ImageWrapper>
      <TitleDiv>[소중한 식사] 경상도 한상차림</TitleDiv>
      <DescriptionDiv>경상도 명물 요리 세가지를 한상에!</DescriptionDiv>
      <PriceWrapper>
        <SalePriceSpan>31200</SalePriceSpan>
        <NetPriceSpan>39000</NetPriceSpan>
      </PriceWrapper>
      <SpecialLabelTag event scene />
    </CardWrapper>
  )
}

const CardWrapper = styled.div`
  width: 384px;
  height: 540px;
`;

const ImageWrapper = styled.div`
  position: relative;
  width: 384px;
  height: 384px;
  margin: 0 0 16px 0;
  &:hover{
    cursor:pointer;
  }
`;

const Image = styled.img`
  width: 100%;
  height: 100%;
`;

const Overlay = styled.div`
  position: absolute;
  display:flex;
  height: 100%;
  width: inherit;
  background: #000;
  bottom: 0;
  opacity: 0;
  transition: .5s ease;
  place-items: center;
  justify-content: center;
  color: #fff;
  &:hover{
    opacity: .6;
  }
`;

const OverlayText = styled.span`
  font-weight: 700;
  font-size: 24px;
  line-height: 35px;
`;

const TitleDiv = styled.div`
  margin: 0 0 8px 0;
  line-height: 23px;
`;

const DescriptionDiv = styled.div`
  font-size: 14px;
  color: #828282;
  margin: 0 0 16px 0;
  line-height: 20px;
`;

const SalePriceSpan = styled.span`
  font-size: 20px;
  font-weight: 700;
  margin: 0 8px 0 0;
  line-height: 29px;
  &::after{
    content:"원";
  }
`;

const NetPriceSpan = styled.span`
  font-size: 14px;
  color: #BDBDBD;
  text-decoration: line-through;
  &::after{
    content:"원";
  }
`;

const PriceWrapper = styled.div`
  margin: 0 0 16px 0;
`;


export default Card;
