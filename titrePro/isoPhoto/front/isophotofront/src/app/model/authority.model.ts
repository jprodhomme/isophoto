export class Authority{

    private  id : number;
    private role : string;

    
    
    constructor( id : number,
        role : string,){
            
            this.id = id;
            this.role = role;
        }
        
        public getId(): number {
            return this.id;
        }
    
        public setId(id: number): void {
            this.id = id;
        }
    
        public getRole(): string {
            return this.role;
        }
    
        public setRole(role: string): void {
            this.role = role;
        }
    
}