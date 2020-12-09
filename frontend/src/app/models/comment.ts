export interface Comment{
    id: number;
    createdAt: string;
    rate: number;
    text: string;
    images: string[];
    user: string;
    culturalOfferId?: number;
}
