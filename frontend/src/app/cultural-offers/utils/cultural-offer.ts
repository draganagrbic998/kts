export interface CulturalOffer{
    id: number;
    category: string;
    type: string;
    placemarkIcon: string;
    name: string;
    description: string;
    image: string;
    location: string;
    lat: number;
    lng: number;
    totalRate: number;
    followed: boolean;
}