export interface News{
    id: number;
    createdAt: Date;
    text: string;
    images: string[];
    culturalOfferId?: number;
}