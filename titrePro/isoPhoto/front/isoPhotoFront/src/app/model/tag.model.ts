export class Tag{

    private id : number;
    private tag : string;

    constructor(
                tag : string){

                   
                    this.tag = tag;
                }
                
    public getId(): number {
        return this.id;
    }

    public setId(id: number): void {
        this.id = id;
    }

    public getTag(): string {
        return this.tag;
    }

    public setTag(tag: string): void {
        this.tag = tag;
    }




}